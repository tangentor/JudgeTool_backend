package cn.judgeTool.client;

import cn.judgeTool.entity.ChatgptReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient("chatgpt")
public interface ChatgptClient {

	@PostMapping("/ask")
	Object ask(ChatgptReq chatgptReq);
}