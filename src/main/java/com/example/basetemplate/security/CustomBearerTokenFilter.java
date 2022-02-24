package com.example.basetemplate.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.filter.GenericFilterBean;

@Slf4j
public class CustomBearerTokenFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		log.debug("Enter");
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
