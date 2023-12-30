package com.nitesh.RatingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.RatingService.entity.Rating;
import com.nitesh.RatingService.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<?> createRating(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
	@GetMapping
	public ResponseEntity<?> findAllRating(){
		return ResponseEntity.status(200).body(ratingService.getAllRating());
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<?> findAllRatingByUserId(@PathVariable String userId){
		return ResponseEntity.status(200).body(ratingService.getAllRatingByUserId(userId));
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<?> findAllRatingByHotelId(@PathVariable String hotelId){
		return ResponseEntity.status(200).body(ratingService.getAllRatingByHotelId(hotelId));
	}
	
}
