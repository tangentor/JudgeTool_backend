package cn.judgeTool.service;

import cn.judgeTool.entity.CaseDetail;
import cn.judgeTool.entity.CrimeCase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
*/
public interface CrimeCaseService {

	/*
	 * 根据罪名获取相关的案件
	 */
	List<CaseDetail> listBriefByCrimeName(String crimeName);

	/**
	 * 获取所有罪名
	 */
	List<String> listAllCrimeName();

	CaseDetail getDetail(String id);

	CaseDetail getDetailByPaperId(String id);
}
