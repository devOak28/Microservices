package com.user.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entitys.User;



public interface UserService {

	// create user
	User saveUser(User user);
	List<User> getAllUser2();
	User getUser(String id) throws Exception;
	
}
