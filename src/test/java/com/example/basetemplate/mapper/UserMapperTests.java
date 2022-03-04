package com.example.basetemplate.mapper;

import java.util.List;

import com.example.basetemplate.config.MapperTestConfig;
import com.example.basetemplate.model.po.PermissionPO;
import com.example.basetemplate.model.po.RolePO;
import com.example.basetemplate.model.po.UserPO;
import com.example.basetemplate.util.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig({MapperTestConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.properties")
@Slf4j
public class UserMapperTests {
	@Autowired
	UserMapper userMapper;

	@BeforeEach
	public void setup(TestInfo testInfo) throws Exception {
		String displayName = testInfo.getDisplayName();

		log.debug("{}: Enter setup", displayName);
	}

	@AfterEach
	public void cleanup(TestInfo testInfo) {
		String displayName = testInfo.getDisplayName();

		log.debug("{}: Leave cleanup", displayName);
	}

	@Test
	@Order(10)
	public void insertUserOK() {
		// given
		UserPO userPONew = UserPO.builder()
				.username("insertUserOK")
				.password("insertUserOK-password")
				.enabled(true)
				.build();

		// when
		int added = userMapper.insertUser(userPONew);

		// then
		assertThat(added).isEqualTo(1);
	}

	@Test
	@Order(20)
	public void findUserByNameOK() {
		// given
		String username = "admin";

		// when
		UserPO userPO = userMapper.findUserByName(username);

		// then
		assertThat(userPO).isNotNull();
		assertThat(userPO.getUsername()).isEqualTo(username);
	}

	@Test
	@Order(100)
	public void insertRoleOK() {
		// given
		RolePO rolePONew = RolePO.builder()
				.name("insertRoleOK")
				.build();

		// when
		int added = userMapper.insertRole(rolePONew);

		// then
		assertThat(added).isEqualTo(1);
	}

	@Test
	@Order(100)
	public void findRolesByName_UsingSpecificName() {
		// given
		String roleName = "sysadmin";

		// when
		List<RolePO> rolePOList = userMapper.findRolesByName(roleName);

		// then
		assertThat(rolePOList).isNotNull();

		log.info("{}", StrUtils.jsonDump(rolePOList));

		assertThat(rolePOList.size()).isEqualTo(1);
	}

	@Test
	@Order(100)
	public void findRolesByName_UsingNullName() {
		// given
		String roleName = null;

		// when
		List<RolePO> rolePOList = userMapper.findRolesByName(roleName);

		// then
		assertThat(rolePOList).isNotNull();

		log.info("{}", StrUtils.jsonDump(rolePOList));

		assertThat(rolePOList.size()).isGreaterThan(1);
	}

	@Test
	@Order(200)
	public void insertPermissionOK() {
		// given
		PermissionPO permissionPONew = PermissionPO.builder()
				.name("insertPermissionOK")
				.build();

		// when
		int added = userMapper.insertPermission(permissionPONew);

		// then
		assertThat(added).isEqualTo(1);
	}

	@Test
	@Order(200)
	public void findPermissionsByName_UsingSpecificName() {
		// given
		String permissionName = "users.create";

		// when
		List<PermissionPO> permissionPOList = userMapper.findPermissionsByName(permissionName);

		// then
		assertThat(permissionPOList).isNotNull();

		log.info("{}", StrUtils.jsonDump(permissionPOList));

		assertThat(permissionPOList.size()).isEqualTo(1);
	}

	@Test
	@Order(200)
	public void findPermissionsByName_UsingNullName() {
		// given
		String permissionName = null;

		// when
		List<PermissionPO> permissionPOList = userMapper.findPermissionsByName(permissionName);

		// then
		assertThat(permissionPOList).isNotNull();

		log.info("{}", StrUtils.jsonDump(permissionPOList));

		assertThat(permissionPOList.size()).isGreaterThan(1);
	}
}
