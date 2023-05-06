package com.gateway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.models.AuthRespone;

@RestController
@RequestMapping("/Auth")
public class AuthController {
	
	private Logger log= LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/login")
	public ResponseEntity<AuthRespone> login(
		@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
		@AuthenticationPrincipal OidcUser user,
		Model model
		){							
		
		log.info("user email id :-"+user.getEmail());
		
		// Creating Auth Response objec
		AuthRespone authRes= new AuthRespone();
		// Creating Auth Response objec
		authRes.setUserId(user.getEmail());
		authRes.setAccessToken(client.getAccessToken().getTokenValue());
		authRes.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
		List<String> authorities= user.getAuthorities().stream().map(grantAuth->{
			return grantAuth.getAuthority();
		}).collect(Collectors.toList());
		
		authRes.setAuthorities(authorities);
		return new ResponseEntity<>(authRes,HttpStatus.OK);
		
	}
	
}
