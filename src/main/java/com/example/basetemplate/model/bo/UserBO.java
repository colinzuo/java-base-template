package com.example.basetemplate.model.bo;

import java.util.Collection;
import java.util.List;

import com.example.basetemplate.model.po.UserPO;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class UserBO implements UserDetails {
	private UserPO userPO;
	private List<GrantedAuthority> authorityList;

	public UserBO(UserPO userPO, List<GrantedAuthority> authorityList) {
		this.userPO = userPO;
		this.authorityList = authorityList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorityList;
	}

	@Override
	public String getPassword() {
		return userPO.getPassword();
	}

	@Override
	public String getUsername() {
		return userPO.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userPO.getEnabled();
	}
}
