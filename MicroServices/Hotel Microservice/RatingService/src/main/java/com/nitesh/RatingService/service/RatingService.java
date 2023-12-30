package com.nitesh.RatingService.service;

import java.util.List;

import com.nitesh.RatingService.entity.Rating;

public interface RatingService {

	Rating create(Rating rating);
	
	List<Rating> getAllRating();
	
	List<Rating> getAllRatingByUserId(String userId);
	
	List<Rating> getAllRatingByHotelId(String id);
}
