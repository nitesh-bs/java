package com.nitesh.HotelService.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.HotelService.entity.Hotel;
import com.nitesh.HotelService.exception.ResourceNotFound;
import com.nitesh.HotelService.repository.HotelRepository;
import com.nitesh.HotelService.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired 
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		String randonId = UUID.randomUUID().toString();
		hotel.setId(randonId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFound("Hotel Not Found!!!"));
	}

}
