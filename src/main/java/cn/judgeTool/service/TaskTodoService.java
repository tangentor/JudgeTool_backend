package cn.judgeTool.service;

import cn.judgeTool.entity.TaskTodo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author a2576
* @description 针对表【t_task_todo】的数据库操作Service
* @createDate 2022-12-17 09:09:18
*/
public interface TaskTodoService extends IService<TaskTodo> {

	boolean allocate(Map map);

	List<TaskTodo> getTodoList(Integer tid);

	List<TaskTodo> getCheckList(Integer tid);

	Map<String,Object> getCheckListWithTotalNum(Integer tid);

	List<Map<String, Object>> getCheckListWithTotalNum();

	Map<String, Object> getCheckListWithTotalNumForAdmin();

	int getCurrentProgress();
}
