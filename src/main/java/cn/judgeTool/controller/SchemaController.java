package cn.judgeTool.controller;

import cn.judgeTool.entity.Schema;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.service.SchemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/schema")
@RestController
@ResponseResult
public class SchemaController {

	@Resource
	private SchemaService schemaService;

	@GetMapping("")
	public List<Schema> listAll(){
		return schemaService.list();
	}
}
