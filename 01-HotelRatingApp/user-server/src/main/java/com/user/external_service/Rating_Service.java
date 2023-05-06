package com.user.external_service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.entitys.Rating;

@Service
@FeignClient(name = "RATING-SERVER")
public interface Rating_Service {

	@PostMapping("/rating-server/rating")
	public Rating createRating(Rating values);
	
//	public Rating updateRating();
	
}
