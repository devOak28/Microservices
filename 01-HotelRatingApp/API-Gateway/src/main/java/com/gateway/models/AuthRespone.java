package com.gateway.models;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRespone {

	
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expireAt;
	private Collection<String> authorities;
}
