package com.authorizationserver.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {
	
	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(auth -> auth
						.anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2
						.opaqueToken()
						.introspectionUri("http://localhost:8088/oauth2/introspect")
						.introspectionClientCredentials("teste-oauth", "123")
						)
				.build();
	}
}
