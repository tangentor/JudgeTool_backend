package cn.judgeTool.controller;

import cn.judgeTool.client.PredictClient;
import cn.judgeTool.entity.WordCut;
import cn.judgeTool.result.ResponseResult;
import cn.judgeTool.util.JSONUtil;
import cn.judgeTool.util.JiebaUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/word")
@ResponseResult
public class WordCutController {

	/**
	 * TODO
	 * 暂时使用这个服务进行分词，后期使用其他的
	 */
	@Resource
	private PredictClient wordCutClient;

	@PostMapping("/cut")
	public WordCut getWordCutList(@RequestBody WordCut wordCut){
//		return JSONUtil.toObject(wordCutClient.wordcut(wordCut),WordCut.class);
		wordCut.setCutList(JiebaUtil.cutWord(wordCut.getWord()));
		return wordCut;
	}
}
