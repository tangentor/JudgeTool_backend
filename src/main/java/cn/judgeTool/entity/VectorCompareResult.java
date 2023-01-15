package cn.judgeTool.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VectorCompareResult implements Comparable<VectorCompareResult>{

	private String id;

	private Double similarity;

	private Cases cases;

	public VectorCompareResult(String id, Double similarity) {
		this.id = id;
		this.similarity = similarity;
	}

	@Override
	public int compareTo(VectorCompareResult o) {
		return (int) (o.similarity*100000-this.similarity*100000);
	}
}
