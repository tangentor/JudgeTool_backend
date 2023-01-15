package cn.judgeTool.controller;

import cn.judgeTool.entity.BlockTodo;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.service.BlockTodoService;
import cn.judgeTool.util.UsernameUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/blockTodo")
@RestController
@ResponseResult
public class BlockTodoController {

	@Resource
	private BlockTodoService blockTodoService;

	@GetMapping("")
	public List<BlockTodo> listByUid() {
	    return blockTodoService.listByUid(UsernameUtil.getLoginUser());
	}

	@PostMapping("")
	public boolean addNewBlockRequestOrUpdate(@RequestBody BlockTodo blockTodo){
		return blockTodoService.insertOrUpdate(blockTodo);
	}
}
