package cn.judgeTool.service.impl;

import cn.judgeTool.client.UserClient;
import cn.judgeTool.entity.User;
import cn.judgeTool.result.Result;
import cn.judgeTool.service.UserService;
import cn.judgeTool.util.JSONUtil;
import cn.judgeTool.util.RedisUtil;
import cn.judgeTool.util.UsernameUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Resource
	private UserClient userClient;

	@Resource
	private RedisUtil redisUtil;

	@Override
	public List<User> listUser() {
		//查看缓存
		Object userList = null;
		if(redisUtil.hasKey("judge:userList")){
			userList = redisUtil.get("judge:userList");
			log.info("{}获取用户列表信息", UsernameUtil.getLoginUser());
		} else {
			userList = JSONUtil.toObject(userClient.listUser(), Result.class).getData() ;
			//设置redis,有效时间一小时
			redisUtil.set("judge:userList",userList,60*60);
		}
		List list = JSONUtil.toObject(userList, List.class);
		ArrayList<User> users = new ArrayList<>(list.size());
		list.forEach(v-> users.add(JSONUtil.toObject(v,User.class)));
		return users;
	}

	@Override
	public User detail(String uid) {
		//缓存中是否存在
		for (User user : listUser()) {
			if(Objects.equals(user.getUsername(),uid)){
				return user;
			}
		}
		return null;
	}
}
