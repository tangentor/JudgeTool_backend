package cn.judgeTool.controller;

import cn.judgeTool.entity.BlockTodo;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.service.BlockTodoService;
import cn.judgeTool.util.UsernameUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

	@GetMapping("/page/{pageNum}")
	public Page<BlockTodo> listPageByUid(@PathVariable int pageNum) {
		return blockTodoService.getPage(pageNum);
	}

	@PostMapping("")
	public boolean addNewBlockRequestOrUpdate(@RequestBody BlockTodo blockTodo){
		return blockTodoService.insertOrUpdate(blockTodo);
	}
}
