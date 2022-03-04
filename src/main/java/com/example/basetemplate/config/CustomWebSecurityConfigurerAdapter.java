package com.example.basetemplate.config;

import com.example.basetemplate.security.CustomAuthenticationFilter;
import com.example.basetemplate.security.CustomAuthenticationProvider;
import com.example.basetemplate.security.CustomBearerTokenFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.logout().disable();
		http.requestCache().disable();
		http.securityContext().disable();
		http.sessionManagement().disable();

		http.removeConfigurer(DefaultLoginPageConfigurer.class);

		http.addFilterBefore(
				customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//		http.addFilterBefore(
//				new CustomBearerTokenFilter(), BasicAuthenticationFilter.class);

		http.cors(withDefaults());

		http.authorizeHttpRequests()
				.mvcMatchers("/actuator/**").permitAll()
				.anyRequest().permitAll();
	}

	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		return new CustomAuthenticationFilter(authenticationManagerBean());
	}

	@Bean(name="authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin(CorsConfiguration.ALL);
		configuration.addAllowedMethod(CorsConfiguration.ALL);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	private CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
}
