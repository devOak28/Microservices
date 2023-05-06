package com.rating.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.entities.Rating;
import com.rating.repo.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepo repo;
	
	@Override
	public Rating saveRating(Rating rating) {
		// TODO Auto-generated method stub
		rating.setRatingId(UUID.randomUUID().toString());
		return repo.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		return repo.findAll();
	}

	@Override
	public List<Rating> getRatingById(String uid) {
		// TODO Auto-generated method stub
		return repo.findByUserId(uid);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		List<Rating> hotelRate=repo.findByHotelId(hotelId);
		System.out.println(hotelId+" hotelId");
		return hotelRate;
	}

}
