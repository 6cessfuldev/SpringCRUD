package com.ezen.second.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
	
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String reg_at;
	private String mod_at;

}
