package cn.judgeTool.controller;

import cn.judgeTool.client.ChatgptClient;
import cn.judgeTool.entity.CaseDetail;
import cn.judgeTool.entity.ChatgptReq;
import cn.judgeTool.entity.Schema;
import cn.judgeTool.service.CrimeCaseService;
import cn.judgeTool.service.SchemaService;
import cn.judgeTool.util.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
@RequestMapping("/chatgpt")
public class ChatgptController {

	@Resource
	private RedisUtil redisUtil;

	@Resource
	private ChatgptClient chatgptClient;

	@Resource
	private CrimeCaseService crimeCaseService;

	@Resource
	private SchemaService schemaService;

	@GetMapping("/ask/{cid}/{sid}")
	public Object ask(@PathVariable String cid, @PathVariable String sid){
		CaseDetail caseDetail = crimeCaseService.getDetailByPaperId(cid);
		Schema schema = schemaService.getById(sid);
		//拼接key
		String key = "judge:aimark:"+cid+":"+sid;
		if(redisUtil.hasKey(key)){
			return redisUtil.get(key);
		}
		//没有缓存，查询gpt
		//拼接问题
		StringBuilder question = new StringBuilder("提取出文中论元，论元有");
		question.append(schema.getArgument())
				.append("，文章为：")
				.append(caseDetail.getBaseInfo());
		Object answer = chatgptClient.ask(new ChatgptReq(question.toString()));
		//设置缓存,有效时间一分钟
		redisUtil.set(key,answer,60);
		return answer;
	}

}
