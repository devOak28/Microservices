package com.rating;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.entities.Rating;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService service;
	
	@PostMapping
	public ResponseEntity<Rating> saveRating(@RequestBody Rating rating){
			
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRating(rating));
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings(){
		return ResponseEntity.ok(service.getAllRatings());
	}
	
	@GetMapping("/user/{uid}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String uid){
		return ResponseEntity.ok(service.getRatingById(uid));
	}
	
	@GetMapping("/hotel/{hid}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hid){
		System.out.println("RatingController.getRatingByHotelId():-");
		List<Rating> res=service.getRatingByHotelId(hid);
		
		return ResponseEntity.ok(res);
	}
}
