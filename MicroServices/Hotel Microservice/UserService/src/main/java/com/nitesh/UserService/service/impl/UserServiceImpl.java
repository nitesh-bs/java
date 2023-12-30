package com.nitesh.UserService.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nitesh.UserService.entity.Hotel;
import com.nitesh.UserService.entity.Rating;
import com.nitesh.UserService.entity.User;
import com.nitesh.UserService.exception.ResourceNotFound;
import com.nitesh.UserService.external.services.HotelService;
import com.nitesh.UserService.repository.UserRepository;
import com.nitesh.UserService.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randonId = UUID.randomUUID().toString();
		user.setUserId(randonId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found!!!"));

		// api call to rating service
		Rating[] ratingsOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),Rating[].class);
		logger.info("{} ", ratingsOfUser);

		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		
		List<Rating> ratingsList = ratings.stream().map(rating -> {
			// api call to hotel service

//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//			logger.info("response status code : {} ", forEntity.getStatusCode());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingsList);
		return user;
	}

}
