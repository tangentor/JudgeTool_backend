package cn.judgeTool.mapper;

import cn.judgeTool.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

/**
* @author a2576
* @description 针对表【t_record】的数据库操作Mapper
* @createDate 2022-12-21 13:45:52
* @Entity cn.judgeTool.entity.Record
*/
@SuppressWarnings("MybatisXMapperMethodInspection")
public interface RecordMapper extends BaseMapper<Record> {

	int selectWeekCount(String uid);

	int selectDayCount(String uid);

	Map<String,Object> selectSotaCount();
}




