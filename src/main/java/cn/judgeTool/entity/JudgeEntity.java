package cn.judgeTool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 案件实体类
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JudgeEntity {

	// 根据caseStr经过MD5之后的字符串
	private String id;

	private String caseStr;

	// 根据案件原始信息进行预测所获得的结果
	private String casePre;

	private List<Double> vector;
}
