package com.authorizationserver.core;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuhtorizationServerConfig {


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{		
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
		return httpSecurity.build();
	}
    
    @Bean
    AuthorizationServerSettings authorizationServerSettings(AuthorizationProperties authorizationProperties) {
    	authorizationProperties.setPrividerUrl("http://localhost:8080");
    	return AuthorizationServerSettings.builder()
    			.issuer(authorizationProperties.getPrividerUrl())
    			.build();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {     	
    	RegisteredClient registeredClient = RegisteredClient
    										.withId("1")
    										.clientId("teste-oauth")
    										.clientSecret(passwordEncoder.encode("123"))
    										.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
    										.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
    										.scopes(ConsumerGrantType.scopeds())
    										.tokenSettings(TokenSettings.builder()
    														.accessTokenFormat(OAuth2TokenFormat.REFERENCE) // opaque Token    														
    														.accessTokenTimeToLive(Duration.ofMinutes(30))    																								
    														.build())
    										.build();
    										
    	return new InMemoryRegisteredClientRepository(Arrays.asList(registeredClient));
    }
               
    @Bean    
    OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations,RegisteredClientRepository registeredClientRepository) {    	    	
    	return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
    }        
}
