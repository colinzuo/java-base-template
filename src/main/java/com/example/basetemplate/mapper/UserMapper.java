package com.example.basetemplate.mapper;

import com.example.basetemplate.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	UserPO findUserByName(String name);
}
