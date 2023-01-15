package cn.judgeTool.controller;

import cn.judgeTool.entity.Record;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.service.RecordService;
import cn.judgeTool.service.TaskTodoService;
import cn.judgeTool.util.UsernameUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseResult
public class DashboardController {

	@Resource
	private RecordService recordService;

	@Resource
	private TaskTodoService taskTodoService;

	@RequestMapping("dashboard")
	public Map<String,Object> dashboardData(){
		String uid = UsernameUtil.getLoginUser();
		HashMap<String, Object> map = new HashMap<>();
		map.put("all",recordService.count(new LambdaQueryWrapper<Record>().eq(Record::getUid,uid)));
		map.put("week",recordService.weekCount(uid));
		map.put("day",recordService.dayCount(uid));
		map.put("progress",taskTodoService.getCurrentProgress());
		map.put("best",recordService.sota());
		return map;
	}
}
