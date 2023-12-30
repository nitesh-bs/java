package com.nitesh.UserService.config.interceptor;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
@Component
public class FeignClientInterceptor  implements RequestInterceptor{
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	    private static final String BEARER_TOKEN_TYPE = "Bearer";
	    private final OAuth2AuthorizedClientService clientService;
	    
	    @Autowired
	    private OAuth2AuthorizedClientManager manager;

	    public FeignClientInterceptor(OAuth2AuthorizedClientService clientService) {
	        this.clientService = clientService;
	    }

	    @Override
	    public void apply(RequestTemplate template) {
	    	
	    	String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
	    	
//	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//	        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
//	                oauthToken.getAuthorizedClientRegistrationId(),
//	                oauthToken.getName());
//
//	        OAuth2AccessToken accessToken = client.getAccessToken();
	        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
	    }

}
