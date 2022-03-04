package com.example.basetemplate.service;

import com.example.basetemplate.mapper.UserMapper;
import com.example.basetemplate.model.bo.UserBO;
import com.example.basetemplate.model.po.UserPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserMapper userMapper;

	@Override
	public UserBO loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPO userPO = userMapper.findUserByName(username);

		if (userPO == null) {
			throw new UsernameNotFoundException("username not found");
		}

		UserBO userBO = new UserBO(userPO, null);

		return userBO;
	}
}
