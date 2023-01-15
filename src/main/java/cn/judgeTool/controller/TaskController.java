package cn.judgeTool.controller;

import cn.judgeTool.entity.Task;
import cn.judgeTool.entity.TaskMember;
import cn.judgeTool.entity.TaskTodo;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.service.TaskMemberService;
import cn.judgeTool.service.TaskService;
import cn.judgeTool.service.TaskTodoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
@ResponseResult
public class TaskController {

	@Resource
	private TaskService taskService;

	@Resource
	private TaskMemberService taskMemberService;

	@Resource
	private TaskTodoService taskTodoService;

	@GetMapping("")
	public List<Task> list(){
		return taskService.listByUser();
	}

	@PostMapping("")
	public boolean add(@RequestBody Task task){
		return taskService.save(task);
	}

	@GetMapping("/member/{tid}")
	public List<TaskMember> listMember(@PathVariable Integer tid){
		LambdaQueryWrapper<TaskMember> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(TaskMember::getTid,tid);
		return taskMemberService.list(wrapper);
	}

	//多个新增
	@PostMapping("/member")
	public boolean addMember(@RequestBody List<TaskMember> taskMembers){
		return taskMemberService.saveBatch(taskMembers);
	}

	@GetMapping("/todo/{tid}")
	public List<TaskTodo> getTodoListByTid(@PathVariable Integer tid){
		return taskTodoService.getTodoList(tid);
	}

	@GetMapping("/checkWithCount")
	public List<Map<String, Object>> getCheckListWithTotalNum(){
		return taskTodoService.getCheckListWithTotalNum();
	}

	@GetMapping("/checkWithCount/admin")
	public Map<String, Object> getCheckListWithTotalNumForAdmin(){
		return taskTodoService.getCheckListWithTotalNumForAdmin();
	}

	@GetMapping("/check/{tid}")
	public List<TaskTodo> getCheckListByTid(@PathVariable Integer tid){
		return taskTodoService.getCheckList(tid);
	}

	@GetMapping("/checkWithCount/{tid}")
	public Map<String, Object> getCheckListWithTotalNum(@PathVariable Integer tid){
		return taskTodoService.getCheckListWithTotalNum(tid);
	}

	//分配任务
	@PostMapping("/todo")
	public boolean allocateTask(@RequestBody Map map){
		return taskTodoService.allocate(map);
	}

	//分配任务
	@GetMapping("/isAllocate/{tid}")
	public boolean isAllocate(@PathVariable String tid){
		LambdaQueryWrapper<TaskTodo> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(TaskTodo::getTid,tid);
		return taskTodoService.list(wrapper).size()!=0;
	}


	@RequestMapping("/control/{tid}")
	public boolean changeTaskControl(@PathVariable Integer tid){
		return taskService.changeTaskControl(tid);
	}
}
