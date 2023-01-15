package cn.judgeTool.controller;

import cn.judgeTool.entity.CaseDetail;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.service.CrimeCaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/crime")
@ResponseResult
public class CrimeCaseController {

	@Resource
	private CrimeCaseService crimeCaseService;

	@GetMapping("/list")
	public List<String> listAllCrimeName(){
		return crimeCaseService.listAllCrimeName();
	}

	@GetMapping("/list/{CrimeName}")
	public List<CaseDetail> getAllInfoByCName(@PathVariable String CrimeName){
		return crimeCaseService.listBriefByCrimeName(CrimeName);
	}

	@GetMapping("/{id}")
	public CaseDetail getCaseDetailById(@PathVariable String id){
		return crimeCaseService.getDetail(id);
	}

	@GetMapping("/paper/{id}")
	public CaseDetail getCaseDetailByPaperId(@PathVariable String id){
		return crimeCaseService.getDetailByPaperId(id);
	}


}
