package com.nitesh.HotelService.controller;

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

import com.nitesh.HotelService.entity.Hotel;
import com.nitesh.HotelService.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PreAuthorize("hasAuthority('Admin ')")
	@PostMapping()
	public ResponseEntity<?> createHotel(@RequestBody Hotel hotel){
		Hotel res = hotelService.create(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
	@GetMapping()
	public ResponseEntity<?> findAllHotels(){
		return ResponseEntity.status(200).body(hotelService.getAllHotels());
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{id}")
	public ResponseEntity<?> finHotelById(@PathVariable String id){
		return ResponseEntity.status(200).body(hotelService.getHotel(id));
	}
}
