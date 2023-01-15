package cn.judgeTool.client;

import cn.judgeTool.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("userservice")
public interface UserClient {

	@PostMapping("/login")
	Object login(User user);

	@GetMapping("/user/list")
	Object listUser();

	@PostMapping("/user/{uid}")
	Object getDetailUserObject(@PathVariable("uid") String uid);
}
