package cn.judgeTool.service;

import cn.judgeTool.entity.JudgeEntity;
import cn.judgeTool.entity.VectorCompareResult;

import java.util.List;

public interface PredictService {

	Object predictFromRaw(JudgeEntity judgeEntity);

	Object getVectorFromPredictResult(JudgeEntity judgeEntity);

	List<VectorCompareResult> getMostSimilarCases(JudgeEntity judgeEntity);
}
