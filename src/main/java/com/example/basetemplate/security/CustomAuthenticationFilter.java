package com.example.basetemplate.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.basetemplate.api.CommonErrorCode;
import com.example.basetemplate.api.ErrorInfo;
import com.example.basetemplate.api.RpcResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthenticationFilter extends OncePerRequestFilter
		implements ApplicationEventPublisherAware {
	private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
			new AntPathRequestMatcher("/login", "POST");

	private RequestMatcher requiresAuthenticationRequestMatcher;
	protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource =
			new WebAuthenticationDetailsSource();

	private AuthenticationManager authenticationManager;

	protected ApplicationEventPublisher eventPublisher;

	private final ObjectMapper objectMapper = new ObjectMapper();

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		Assert.notNull(authenticationManager, "authenticationManager cannot be null");

		this.requiresAuthenticationRequestMatcher = DEFAULT_ANT_PATH_REQUEST_MATCHER;
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!requiresAuthentication(request, response)) {
			chain.doFilter(request, response);
			return;
		}

		try {
			LoginRequest loginRequest = objectMapper.readValue(
					request.getInputStream(), LoginRequest.class);

			CustomAuthentication authRequest = new CustomAuthentication(loginRequest, null);
			setDetails(request, authRequest);

			Authentication authResult = this.authenticationManager.authenticate(authRequest);

			onSuccessfulAuthentication(request, response, authResult);
		}
		catch (Exception ex) {
			SecurityContextHolder.clearContext();

			this.logger.debug("Failed to process authentication request", ex);

			onUnsuccessfulAuthentication(request, response, ex);
		}
	}

	private boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (this.requiresAuthenticationRequestMatcher.matches(request)) {
			return true;
		}
		if (this.logger.isTraceEnabled()) {
			this.logger
					.trace(LogMessage.format("Did not match request to %s", this.requiresAuthenticationRequestMatcher));
		}
		return false;
	}

	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
	}

	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);

		if (this.logger.isDebugEnabled()) {
			this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
		}

		if (this.eventPublisher != null) {
			this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
		}

		LoginResponseData loginResponseData = LoginResponseData.builder()
				.userId(100L)
				.userName("demo")
				.token("demo-token")
				.build();
		RpcResponse<LoginResponseData> loginResponse = new RpcResponse<>();
		loginResponse.setData(loginResponseData);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		objectMapper.writeValue(response.getOutputStream(), loginResponse);

		response.getOutputStream().flush();
	}

	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Exception failed) throws IOException {
		SecurityContextHolder.clearContext();

		this.logger.trace("Failed to process authentication request", failed);
		this.logger.trace("Cleared SecurityContextHolder");
		this.logger.trace("Handling authentication failure");

		RpcResponse<LoginResponseData> loginResponse = new RpcResponse<>();

		ErrorInfo errorInfo = ErrorInfo.of(CommonErrorCode.RES_UNAUTHORIZED);

		loginResponse.setError(errorInfo);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		objectMapper.writeValue(response.getOutputStream(), loginResponse);

		response.getOutputStream().flush();
	}

	protected AuthenticationManager getAuthenticationManager() {
		return this.authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public final void setRequiresAuthenticationRequestMatcher(RequestMatcher requestMatcher) {
		Assert.notNull(requestMatcher, "requestMatcher cannot be null");
		this.requiresAuthenticationRequestMatcher = requestMatcher;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void setAuthenticationDetailsSource(
			AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
		Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
		this.authenticationDetailsSource = authenticationDetailsSource;
	}
}
