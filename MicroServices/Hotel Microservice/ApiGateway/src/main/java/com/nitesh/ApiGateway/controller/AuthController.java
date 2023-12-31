package com.nitesh.ApiGateway.controller;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.ApiGateway.model.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/login")
	public ResponseEntity<?> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,@AuthenticationPrincipal OidcUser user,Model model
			){

		logger.info("user email id : {} ",user.getEmail());
		
		AuthResponse authResponse = new AuthResponse(user.getEmail(), client.getAccessToken().getTokenValue(), client.getRefreshToken().getTokenValue(), client.getAccessToken().getExpiresAt().getEpochSecond(), user.getAuthorities().stream().map(grantedAuthority ->{
			return grantedAuthority.getAuthority();
		}).collect(Collectors.toList()));
		
		return ResponseEntity.status(200).body(authResponse);
	}
}
