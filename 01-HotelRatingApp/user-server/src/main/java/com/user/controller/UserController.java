package com.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.entitys.Hotel;
import com.user.entitys.Rating;
import com.user.entitys.User;
import com.user.external_service.Hotel_Service;
import com.user.services.UserSerivceImpl;
import com.user.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {

	/*
	 * @Autowired UserService service;
	 */
	@Autowired
	UserSerivceImpl service;
	
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		System.out.println(user);
		User user1 = service.saveUser(user);
		System.out.println(user1);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	int retryCount=0;
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//	@Retry (name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
	@RateLimiter (name = "userRateLimiter",fallbackMethod = "ratingHotelFallBack" )
	public ResponseEntity<User> getUser(@PathVariable String userId) throws Exception{
		
		System.out.println("Retry Count::-"+retryCount);
		retryCount++;
		User res=service.getUser(userId);
		
		return ResponseEntity.ok(res);
	}
	
		public ResponseEntity<User> ratingHotelFallBack(String userId,Exception ex){
		System.out.println("Fallback is executed becasue service is down "+ex.getMessage());
		User res=User.builder().
					   email("dummy@gmail.com").name("Dummy Data").about("this is dummy user").userId("123456").build();
		return new ResponseEntity<User>(res,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser=service.getAllUser2();
		return ResponseEntity.ok(allUser);
	}
}
