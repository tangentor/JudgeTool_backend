package cn.judgeTool.mapper;

import cn.judgeTool.entity.CrimeCase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author a2576
* @description 针对表【t_crime_case】的数据库操作Mapper
* @createDate 2022-11-30 14:32:02
* @Entity cn.judgeTool.entity.CrimeCase
*/
public interface CrimeCaseMapper extends BaseMapper<CrimeCase> {

	List<String> selectAllNamesWithNum();

}




