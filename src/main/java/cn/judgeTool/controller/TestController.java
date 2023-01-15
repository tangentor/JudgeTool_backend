package cn.judgeTool.controller;

import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.util.UsernameUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class TestController {

	@GetMapping("/test")
	@ResponseResult
	public Object test(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("张三",new ArrayList<>());
		map.put("dddd",111);
		map.put("shjahs99哼哼",ResponseResult.class);
		return map;
	}

	@GetMapping("/testLocal")
	@ResponseResult
	public Object testLocal(){
		return UsernameUtil.getLoginUser();
	}
}
