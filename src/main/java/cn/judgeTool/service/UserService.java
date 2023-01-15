package cn.judgeTool.service;

import cn.judgeTool.entity.User;

import java.util.List;

public interface UserService {

	List<User> listUser();

	User detail(String uid);
}
