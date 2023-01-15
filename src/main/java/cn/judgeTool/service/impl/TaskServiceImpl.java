package cn.judgeTool.service.impl;

import cn.judgeTool.entity.TaskTodo;
import cn.judgeTool.mapper.TaskTodoMapper;
import cn.judgeTool.util.UsernameUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.judgeTool.entity.Task;
import cn.judgeTool.service.TaskService;
import cn.judgeTool.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author a2576
* @description 针对表【t_task】的数据库操作Service实现
* @createDate 2022-12-17 09:09:18
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

	@Resource
	private TaskMapper taskMapper;

	@Resource
	private TaskTodoMapper taskTodoMapper;

	@Override
	public List<Task> listByUser() {
		//若是管理员
//		User
		//todo 仅仅给出与当前用户相关的
		return this.list();
	}

	@Override
	public boolean changeTaskControl(Integer tid) {
		Task task = taskMapper.selectById(tid);
		//验证Task
		//若是标注人
		if (task.getMarkman().contains(UsernameUtil.getLoginUser())) {
			task.setStatus(1);
		} else if(task.getValidman().contains(UsernameUtil.getLoginUser())){
			task.setStatus(0);
		} else {
			throw new RuntimeException("当前用户与当前任务无关系");
		}
		return this.updateById(task);
	}

	@Override
	public Task getByTodoId(String todoId) {
		TaskTodo taskTodo = taskTodoMapper.selectById(todoId);
		//再获取task
		return taskMapper.selectById(taskTodo.getTid());
	}
}




