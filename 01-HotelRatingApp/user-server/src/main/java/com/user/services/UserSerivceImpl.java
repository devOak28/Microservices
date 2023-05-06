package com.user.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entitys.Hotel;
import com.user.entitys.Rating;
import com.user.entitys.User;
import com.user.exception.ResourceNotFoundException;
import com.user.external_service.Hotel_Service;
import com.user.repo.UserRepository;

@Service
public class UserSerivceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private Hotel_Service hotelService;
	
	
	@Override
	public User saveUser(User user) {
		String randomeUserId = UUID.randomUUID().toString();
		user.setUserId(randomeUserId);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUser2() {
		
		//User user =userRepo.findAll().orElseThrow(()-> new ResourceNotFoundException("User id "+id+" not found in server"));
		
		List<User> user =userRepo.findAll();
	//	Rating[] ratingArr=restTemplate.getForObject("http://RATING-SERVICE/rating",Rating[].class);
		
	//	List<Rating> ratingObj=Arrays.stream(ratingArr).collect(Collectors.toList());
		
/*		List<Rating> rateList= ratingObj.stream().map(rating->{
			//Api call to hotel to get the service
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// Set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList()); */
		
		
		//user.stream().forEach(u-> u.setRatings(rateList));
		
		return user;
	}

//	@Override
//	public User getUser(String id) throws Exception {
//		// TODO Auto-generated method stub
//		return userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User id "+id+" not found in server"));
//	}

	@Override
	public User getUser(String id) throws Exception {
		// TODO Auto-generated method stub
		User user =userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User id "+id+" not found in server"));
		
		Rating[] ratingArr=restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+id,Rating[].class);
		
		List<Rating> ratingObj=Arrays.stream(ratingArr).collect(Collectors.toList());
		
		//Hotel hotel2 = hotelService.getHotel(ratingObj.get(0).getHotelId());
		
		List<Rating> rateList= ratingObj.stream().map(rating->{
			//Api call to hotel to get the service
		//	ResponseEntity<Hotel> resEntity=restTemplate.getForEntity("http://HOTEL-SERVER/hotel-server/hotel/"+rating.getHotelId(),Hotel.class);
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//			System.out.println("Status code:-"+resEntity.getStatusCodeValue());
			// Set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());
		
		
		user.setRatings(rateList);
		
		System.out.println("Calling rating service"+user.toString());
		return user;
	}

	
	
	
}
