package com.example.basetemplate.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseData {
	private Long userId;
	private String userName;
	private String token;
}
