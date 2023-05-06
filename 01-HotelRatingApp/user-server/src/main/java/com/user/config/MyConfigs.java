package com.user.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.user.config.interceptor.RestTemplateInterceptor;

@Configuration
public class MyConfigs {

	
	@Autowired
	ClientRegistrationRepository clientRestiRepo;
	@Autowired
	OAuth2AuthorizedClientRepository clientRepo;
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		RestTemplate restTemp= new RestTemplate();
		
		List<ClientHttpRequestInterceptor> restTempInterceptor= new ArrayList<ClientHttpRequestInterceptor>();
		restTempInterceptor.add(new RestTemplateInterceptor(manager(
				clientRestiRepo,
				clientRepo
				)));
		
		restTemp.setInterceptors(restTempInterceptor);
	
		return restTemp;
	
	}
	
	// decalare the bean of OAuthAuthorizedClientManager
	@Bean
	public OAuth2AuthorizedClientManager manager(
			ClientRegistrationRepository clientRepo,
			OAuth2AuthorizedClientRepository oauthRepo) {
		
		OAuth2AuthorizedClientProvider provider=OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
				
		
		DefaultOAuth2AuthorizedClientManager defaultAuthManager=new DefaultOAuth2AuthorizedClientManager(clientRepo, oauthRepo);
		
		defaultAuthManager.setAuthorizedClientProvider(provider);
	
		return defaultAuthManager;
	}
	
}
