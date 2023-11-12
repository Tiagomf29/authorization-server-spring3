package com.authorizationserver.core;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.security.oauth2.core.AuthorizationGrantType;

public class ConsumerGrantType {
	
	public static Consumer<Set<AuthorizationGrantType>> types(){		
	 	Set<AuthorizationGrantType> lista = new HashSet<>();
	 	lista.add(AuthorizationGrantType.REFRESH_TOKEN);
    	lista.add(AuthorizationGrantType.CLIENT_CREDENTIALS);    	
    	Consumer<Set<AuthorizationGrantType>> consumer = (a -> a.addAll(lista));
		return consumer;
	}
	
	public static Consumer<Set<String>> scopeds(){
		Set<String> lista = new HashSet<>();
    	lista.add("READ");
    	lista.add("WRITE");    	
    	Consumer<Set<String>> consumer = (a -> a.addAll(lista));
    	
    	return consumer;
	}

}
