package cn.judgeTool.service.impl;

import cn.judgeTool.entity.CrimeCase;
import cn.judgeTool.entity.Task;
import cn.judgeTool.mapper.CrimeCaseMapper;
import cn.judgeTool.mapper.TaskMapper;
import cn.judgeTool.util.UsernameUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.judgeTool.entity.TaskTodo;
import cn.judgeTool.service.TaskTodoService;
import cn.judgeTool.mapper.TaskTodoMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author a2576
* @description 针对表【t_task_todo】的数据库操作Service实现
* @createDate 2022-12-17 09:09:18
*/
@Service
public class TaskTodoServiceImpl extends ServiceImpl<TaskTodoMapper, TaskTodo>
    implements TaskTodoService{

	@Resource
	private CrimeCaseMapper crimeCaseMapper;

	@Resource
	private TaskTodoMapper taskTodoMapper;

	@Resource
	private TaskMapper taskMapper;

	@SneakyThrows
	@Override
	public boolean allocate(Map map) {
		//获取传递的参数
		// uid
		Map<String,List<String>> uid =  (Map<String, List<String>>) map.get("uid");
		System.out.println(uid);
		//group_key
		List<String> groupKey = (List)map.get("groupKey");
		//tid
		Object tid = map.get("tid");
		//所有的罪名
		List crimeList = (List)map.get("crimeList");
		LambdaQueryWrapper<CrimeCase> wrapper = new LambdaQueryWrapper<>();
		wrapper.in(CrimeCase::getCrime,crimeList);
		//查询所有符合的记录
		List<CrimeCase> crimeCases = crimeCaseMapper.selectList(wrapper);
		//添加的记录
		List<TaskTodo> entityList = new ArrayList<>();
		//轮询添加
		int flagG = 0;
		for(CrimeCase item:crimeCases){
			TaskTodo taskTodo = new TaskTodo();
			//轮到的group
			String key = groupKey.get(flagG++ % groupKey.size());
			if("验证".equals(key)){
				continue;
			}
			taskTodo.setGroupKey(key);
			//paper_id
			taskTodo.setPaperId(item.getDetailId());
			//tid
			taskTodo.setTid(Integer.parseInt(tid.toString()));
			//一个group多份
			for (String ruid : uid.get(key)) {
				taskTodo.setUid(ruid);
//				System.out.println(taskTodo);
				entityList.add(taskTodo.clone());
			}
		}
		//批量添加
//		return true;
		return this.saveBatch(entityList);
	}

	@Override
	public List<TaskTodo> getTodoList(Integer tid) {
		LambdaQueryWrapper<TaskTodo> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(TaskTodo::getTid,tid);
		//通过token获取指定的人
		wrapper.eq(TaskTodo::getUid, UsernameUtil.getLoginUser());
		wrapper.eq(TaskTodo::getStatus,0);
		return this.list(wrapper);
	}

	@Override
	public List<TaskTodo> getCheckList(Integer tid) {
		String username = UsernameUtil.getLoginUser();
		//判断检验身份
		LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
		//完成SQL：SELECT id,name,remark,markman,validman,status FROM t_task WHERE id = 2 AND (validman LIKE '%1003%' OR markman LIKE '%1003%')
		wrapper.and(i->i.eq(Task::getId,tid))
				.and(i->i.like(Task::getValidman,username)
						.or()
						.like(Task::getMarkman,username));
		Task task = taskMapper.selectOne(wrapper);
		if(task == null){
			return null;
		}
		LambdaQueryWrapper<TaskTodo> wrapper2 = new LambdaQueryWrapper<>();
		wrapper2.eq(TaskTodo::getTid,tid);
		wrapper2.eq(TaskTodo::getStatus,1);
		return this.list(wrapper2);
	}

	@Override
	public Map<String, Object> getCheckListWithTotalNum(Integer tid) {
		HashMap<String, Object> res = new HashMap<>();
		//总数
		res.put("count",this.count());
		//未标注结果
		res.put("unmark",this.getCheckList(tid));
		return res;
	}

	// 获取所有的标注任务及总数
	@Override
	public List<Map<String, Object>> getCheckListWithTotalNum() {
		return getListByUid(UsernameUtil.getLoginUser());
	}

	private List<Map<String, Object>> getListByUid(String uid){
		List<Map<String, Object>> maps = new ArrayList<>();
		//总相关任务数
		LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
		wrapper.like(Task::getMarkman,uid);
		List<Task> tasks = taskMapper.selectList(wrapper);
		for (Task task : tasks) {
			HashMap<String, Object> res = new HashMap<>();
			//每个任务的完成数与总数
			LambdaQueryWrapper<TaskTodo> wrapper2 = new LambdaQueryWrapper<>();
			wrapper2.eq(TaskTodo::getTid,task.getId());
			int total = this.count(wrapper2);
			res.put("total",total);
			//已完成的
			wrapper2.eq(TaskTodo::getStatus,1);
			int marked = this.count(wrapper2);
			res.put("marked",marked);
			res.put("task",task);
			maps.add(res);
		}
		return maps;
	}


	@Override
	public Map<String, Object> getCheckListWithTotalNumForAdmin() {
		Map<String, Object> map = new HashMap<>();
		//总相关任务数
		QueryWrapper<TaskTodo> allUsers = new QueryWrapper<TaskTodo>().select("DISTINCT uid");
		for (TaskTodo taskTodo : taskTodoMapper.selectList(allUsers)) {
			List<Map<String, Object>> item = getListByUid(taskTodo.getUid());
			map.put(taskTodo.getUid(),item);
		}
		return map;
	}

	@Override
	public int getCurrentProgress() {
		//查询总条数
		int all = this.count();
		QueryWrapper<TaskTodo> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        int undo = taskTodoMapper.selectCount(wrapper);
        int done = all - undo;
		return Math.round(((float)done/(float)all)*100);
	}
}




