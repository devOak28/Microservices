package com.hotel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class HotelStaffController {

	
	@GetMapping
	public List<String> getStaffsMembers(){
		
		return Arrays.asList("Ram","Sita","Geeta","Nargis");
	}
}
