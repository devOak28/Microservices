package com.user.external_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.user.entitys.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface Hotel_Service {

	@GetMapping("/hotel/{hid}")
	public Hotel getHotel(@PathVariable String hid);
	

	
}
