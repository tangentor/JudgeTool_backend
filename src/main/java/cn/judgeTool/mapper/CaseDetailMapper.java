package cn.judgeTool.mapper;

import cn.judgeTool.entity.CaseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author a2576
* @description 针对表【t_case_detail】的数据库操作Mapper
* @createDate 2022-11-30 14:32:01
* @Entity cn.judgeTool.entity.CaseDetail
*/
public interface CaseDetailMapper extends BaseMapper<CaseDetail> {


	CaseDetail selectBriefByPaperId(String paperId);
}




