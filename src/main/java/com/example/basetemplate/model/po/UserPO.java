package com.example.basetemplate.model.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPO {
	private Long id;
	private String username;
	private String password;
}
