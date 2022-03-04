package com.example.basetemplate.mapper;

import java.util.List;

import com.example.basetemplate.model.po.PermissionPO;
import com.example.basetemplate.model.po.RolePO;
import com.example.basetemplate.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	int insertUser(UserPO user);
	UserPO findUserByName(String name);

	int insertRole(RolePO role);
	List<RolePO> findRolesByName(String name);

	int insertPermission(PermissionPO permission);
	List<PermissionPO> findPermissionsByName(String name);

//	List<RolePO> findRolesByNameAndUserId(String name, Long userId);
}
