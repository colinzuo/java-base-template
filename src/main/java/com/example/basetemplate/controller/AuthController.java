package com.example.basetemplate.controller;

import com.example.basetemplate.api.RpcResponse;
import com.example.basetemplate.mapper.UserMapper;
import com.example.basetemplate.security.LoginRequest;
import com.example.basetemplate.security.LoginResponseData;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base-template-api/v1/auth")
@Slf4j
public class AuthController {
	@Autowired
	UserMapper userMapper;

	@PostMapping(value = "/login")
	public RpcResponse<LoginResponseData> create(@RequestBody LoginRequest loginRequest) {
		log.info("{}", userMapper.findUserByName("admin"));

		LoginResponseData loginResponseData = LoginResponseData.builder()
				.userId(100L)
				.userName("demo")
				.token("demo-token")
				.build();
		RpcResponse<LoginResponseData> loginResponse = new RpcResponse<>();
		loginResponse.setData(loginResponseData);

		return loginResponse;
	}
}
