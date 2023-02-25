package cn.judgeTool.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatgptReq {
	private String question;

	private Object answer;

	public ChatgptReq(String question){
		this.question = question;
	}
}
