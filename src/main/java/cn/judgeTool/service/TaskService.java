package cn.judgeTool.service;

import cn.judgeTool.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author a2576
* @description 针对表【t_task】的数据库操作Service
* @createDate 2022-12-17 09:09:18
*/
public interface TaskService extends IService<Task> {

	List<Task> listByUser();

	boolean changeTaskControl(Integer tid);

	Task getByTodoId(String todoId);
}
