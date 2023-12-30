package com.nitesh.HotelService.service;

import java.util.List;

import com.nitesh.HotelService.entity.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);
	
	List<Hotel> getAllHotels();
	
	Hotel getHotel(String id);
}
