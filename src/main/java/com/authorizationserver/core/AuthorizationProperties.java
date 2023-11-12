package com.authorizationserver.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
@ConfigurationProperties("teste.auth")
public class AuthorizationProperties {
    private String prividerUrl;
}
