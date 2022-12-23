package com.ezen.second.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

	private String id;
	private String pw;
	private String email;
	private String name;
	private int age;
	private String home;
	
}
