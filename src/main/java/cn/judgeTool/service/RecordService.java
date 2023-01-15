package cn.judgeTool.service;

import cn.judgeTool.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author a2576
* @description 针对表【t_record】的数据库操作Service
* @createDate 2022-12-21 13:45:52
*/
public interface RecordService extends IService<Record> {

	boolean saveAndUpdateTodo(Record record);

	int weekCount(String uid);

	int dayCount(String uid);

	String sota();
}
