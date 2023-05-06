package com.rating.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.rating.entities.Rating;

@Service
public interface RatingService {

	public Rating saveRating(Rating rating);
	public List<Rating> getAllRatings();
	public List<Rating> getRatingById(String uid);
	public List<Rating> getRatingByHotelId(String hotelId);
}
