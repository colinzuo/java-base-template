package com.example.basetemplate.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LoginRequest loginRequest = (LoginRequest) authentication.getPrincipal();

		if (loginRequest.getKind().equals("UsernamePassword")) {
			return authenticateUsernamePassword(loginRequest);
		} else {
			throw new BadCredentialsException("Unsupported login kind");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return CustomAuthentication.class.equals(authentication);
	}

	private Authentication authenticateUsernamePassword(LoginRequest loginRequest) throws AuthenticationException {
		return null;
	}
}
