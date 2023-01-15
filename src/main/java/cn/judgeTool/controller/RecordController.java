package cn.judgeTool.controller;

import cn.judgeTool.entity.Record;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.service.RecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/record")
@ResponseResult
public class RecordController {

	@Resource
	private RecordService recordService;

	@PostMapping("")
	public boolean add(@RequestBody Record record){
		return recordService.saveAndUpdateTodo(record);
	}

	@GetMapping("/{id}")
	public Record getDetail(@PathVariable String id){
		return recordService.getById(id);
	}
}
