package com.rating.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rating.entities.Rating;

@Repository
public interface RatingRepo extends JpaRepository<Rating, String>{
	

	
	public List<Rating> findByUserId(String uid);

	
	public List<Rating> findByHotelId(String hotelId);
}
