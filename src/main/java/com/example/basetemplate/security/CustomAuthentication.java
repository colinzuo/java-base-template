package com.example.basetemplate.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthentication extends UsernamePasswordAuthenticationToken {
	public CustomAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	// this constructor creates a Authentication instance which is fully authenticated
	public CustomAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
