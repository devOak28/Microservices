package com.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.entity.Hotel;

@Service
public interface HotelService {

	public Hotel getHotel(String id) throws Exception;
	public Hotel saveHotel(Hotel hotel);
	public List<Hotel> getAllHotel();
	
}
