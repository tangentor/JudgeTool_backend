package cn.judgeTool.client;

import cn.judgeTool.entity.JudgeEntity;
import cn.judgeTool.entity.WordCut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("DMBERT-predict-py")
public interface PredictClient {

	@PostMapping("/predict")
	Object predict(JudgeEntity judgeEntity);

	@PostMapping("/vector")
	String vector(JudgeEntity judgeEntity);

	@PostMapping("/wordcut")
	String wordcut(WordCut wordCut);

}
