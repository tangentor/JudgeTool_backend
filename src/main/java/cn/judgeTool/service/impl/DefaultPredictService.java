package cn.judgeTool.service.impl;

import cn.judgeTool.client.PredictClient;
import cn.judgeTool.entity.Cases;
import cn.judgeTool.entity.JudgeEntity;
import cn.judgeTool.entity.VectorCompareResult;
import cn.judgeTool.mapper.CasesMapper;
import cn.judgeTool.service.PredictService;
import cn.judgeTool.util.JSONUtil;
import cn.judgeTool.util.MathTool;
import cn.judgeTool.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DefaultPredictService implements PredictService {

	@Resource
	private PredictClient predictClient;

	@Resource
	private RedisUtil redisUtil;

	@Resource
	private CasesMapper casesMapper;

	@Value("${predict.enableCache}")
	private boolean enableCache;

	/**
	 * 根据原始文本进行预测
	 */
	@Override
	public Object predictFromRaw(JudgeEntity judgeEntity) {
		// 判断是否有缓存
		String md5 = DigestUtils.md5DigestAsHex(judgeEntity.getCaseStr().getBytes(StandardCharsets.UTF_8));
		String id = "tool:predict:" + md5;
		if(redisUtil.hasKey(id)){
			return redisUtil.get(id);
		} else {
			// 设置缓存
			Object predict = predictClient.predict(judgeEntity);
			redisUtil.set(id,predict);
			return predict;
		}
	}

	@Override
	public Object getVectorFromPredictResult(JudgeEntity judgeEntity) {
		return predictClient.vector(judgeEntity);
	}

	@Override
	public List<VectorCompareResult> getMostSimilarCases(JudgeEntity judgeEntity) {
		String vector = (String) getVectorFromPredictResult(judgeEntity);
		// 获取到传入的文本的向量信息
		ArrayList targetVector = JSONUtil.toObject(vector, ArrayList.class);
		// 与数据库中的向量做比对
		// 获取全部的案件的id与向量
		ArrayList<VectorCompareResult> vectors = new ArrayList<>();

		for (Object obj : listAllCaseVectorAndId()) {
			Cases cases = JSONUtil.toObject(obj, Cases.class);
			ArrayList tmpList = JSONUtil.toObject(cases.getTypevector(), ArrayList.class);
			VectorCompareResult var = new VectorCompareResult(cases.getId(), MathTool.similarity(targetVector, tmpList));
			vectors.add(var);
		}
		// 排序
		Collections.sort(vectors);
		// 显示最高的50篇，并获取具体信息并返回
		List<VectorCompareResult> res = vectors.subList(0, 20);
		for(VectorCompareResult item: res){
			item.setCases(getCaseDetailById(item.getId()));
		}
		return res;
	}

	// 获取所有案件的id以及向量
	private List listAllCaseVectorAndId() {
		String countKey = "tool:vector:count";
		String vectorKey = "tool:vector:all";
		int countInReal = casesMapper.selectCount(null);
		if(enableCache){
			int countInCache = 0;
			if (redisUtil.hasKey(countKey)) {
				countInCache = Integer.parseInt(redisUtil.get(countKey).toString());
			}
			if (redisUtil.hasKey(countKey)) {
				// 查看缓存中的总数与数据库中的是否一致
				if (countInReal == countInCache) {
					return JSONUtil.toObject(redisUtil.get(vectorKey), ArrayList.class);
				}
			}
		}
		LambdaQueryWrapper<Cases> wrapper = new LambdaQueryWrapper<>();
		wrapper.select(Cases::getId,Cases::getTypevector);
		List<Cases> VectorsWithId = casesMapper.selectList(wrapper);
		if(enableCache){
			redisUtil.set(vectorKey,VectorsWithId);
			redisUtil.set(countKey,countInReal);
		}
		return VectorsWithId;
	}

	// 通过Id获取案件的详情,缓存有效时间30min
	private Cases getCaseDetailById(String id){
		// 加上缓存
//		String key = "tool:cases:"+id;
//		if(redisUtil.hasKey(key)){
//			return JSONUtil.toObject(redisUtil.get(key),Cases.class);
//		} else {
//			Cases cases = casesMapper.selectById(id);
//			redisUtil.set(key,cases,60*30);
//			return cases;
//		}
		return casesMapper.selectById(id);
	}
}
