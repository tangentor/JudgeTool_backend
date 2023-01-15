package cn.judgeTool.controller;

import cn.judgeTool.client.UserClient;
import cn.judgeTool.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

	@Resource
	private UserClient loginService;

	@PostMapping("/login")
	public Object login(@RequestBody User user){
		return loginService.login(user);
	}

}
