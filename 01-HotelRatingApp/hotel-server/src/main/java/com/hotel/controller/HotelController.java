package com.hotel.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entity.Hotel;
import com.hotel.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	
	@Autowired
	private HotelService service;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping()
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveHotel(hotel));
	}
	
	@PreAuthorize("hasAuthority('SCOPE_profile')")
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String id) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(service.getHotel(id));
	}
	
	
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels(){
		return ResponseEntity.ok(service.getAllHotel());
	}

	
}
