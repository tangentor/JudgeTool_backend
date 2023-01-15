package cn.judgeTool.service.impl;

import cn.judgeTool.entity.CaseDetail;
import cn.judgeTool.entity.CrimeCase;
import cn.judgeTool.mapper.CaseDetailMapper;
import cn.judgeTool.mapper.CrimeCaseMapper;
import cn.judgeTool.service.CrimeCaseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrimeCaseServiceImpl implements CrimeCaseService {

	@Resource
	private CrimeCaseMapper crimeCaseMapper;

	@Resource
	private CaseDetailMapper caseDetailMapper;

	@Override
	public List<CaseDetail> listBriefByCrimeName(String crimeName) {
		LambdaQueryWrapper<CrimeCase> wrapper = new LambdaQueryWrapper<>();
		// 查询条件
		wrapper.eq(CrimeCase::getCrime,crimeName);
		// 映射并返回结果
		ArrayList<CaseDetail> caseDetails = new ArrayList<>();
//		for (CrimeCase crimeCase : crimeCaseMapper.selectList(wrapper)) {
//			LambdaQueryWrapper<CaseDetail> wcd = new LambdaQueryWrapper<CaseDetail>()
//					.eq(CaseDetail::getPaperId, crimeCase.getDetailId())
//					.select(CaseDetail::getName, CaseDetail::getResult,CaseDetail::getId);
//			System.out.println("uuu");
//			caseDetails.add(caseDetailMapper.selectOne(wcd));
//		}
//		return caseDetails;

		return crimeCaseMapper.selectList(wrapper).stream()
				.map(cm-> caseDetailMapper.selectBriefByPaperId(cm.getDetailId()))
				.collect(Collectors.toList());
	}

	@Override
	public List<String> listAllCrimeName() {
		return crimeCaseMapper.selectAllNamesWithNum();
	}

	@Override
	public CaseDetail getDetail(String id) {
		return caseDetailMapper.selectById(id);
	}

	@Override
	public CaseDetail getDetailByPaperId(String id) {
		LambdaQueryWrapper<CaseDetail> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(CaseDetail::getPaperId,id);
		return caseDetailMapper.selectOne(wrapper);
	}
}
