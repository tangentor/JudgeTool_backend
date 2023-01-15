package cn.judgeTool.util;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.List;
import java.util.stream.Collectors;

public class JiebaUtil {

	private static JiebaSegmenter segmenter = new JiebaSegmenter();

	public static List<String> cutWord(String text) {
		List<SegToken> res = segmenter.process(text, JiebaSegmenter.SegMode.SEARCH);
		return res.stream().map(v -> v.word).collect(Collectors.toList());
	}


}
