package cn.judgeTool.controller;

import cn.judgeTool.client.UserClient;
import cn.judgeTool.entity.User;
import cn.judgeTool.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/list")
	public List<User> list(){
		return userService.listUser();
	}




}
