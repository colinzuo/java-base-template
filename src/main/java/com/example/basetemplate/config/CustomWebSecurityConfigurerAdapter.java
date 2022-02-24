package com.example.basetemplate.config;

import com.example.basetemplate.security.CustomBearerTokenFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
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
				new CustomBearerTokenFilter(), BasicAuthenticationFilter.class);

		http.cors(withDefaults());

		http.authorizeHttpRequests()
				.mvcMatchers("/actuator/**").permitAll()
				.anyRequest().permitAll();
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
}
