package com.hotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entity.Hotel;
import com.hotel.repository.HotelRepo;
@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo repo;
	
	@Override
	public Hotel getHotel(String id) throws Exception {
		return repo.findById(id).orElseThrow(()->new Exception(""));
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		String hotelId=UUID.randomUUID().toString();
		hotel.setHotelId(hotelId);
		return repo.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return repo.findAll();
	}

}
