package cn.judgeTool.controller;

import cn.judgeTool.client.PredictClient;
import cn.judgeTool.entity.JudgeEntity;
import cn.judgeTool.entity.VectorCompareResult;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.service.PredictService;
import cn.judgeTool.util.RedisUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@ResponseResult
public class PredictController {

	@Resource
	private PredictService predictService;

	@PostMapping("/predict")
	public Object predict(@RequestBody JudgeEntity judgeEntity){
		return predictService.predictFromRaw(judgeEntity);
	}

	@PostMapping("/similar")
	public List<VectorCompareResult> similar(@RequestBody JudgeEntity judgeEntity ){
		return predictService.getMostSimilarCases(judgeEntity);
	}
}
