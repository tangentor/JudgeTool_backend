package cn.judgeTool.entity;

import lombok.Data;

import java.util.List;

@Data
public class WordCut {

	private String word;

	private List<String> cutList;
}
