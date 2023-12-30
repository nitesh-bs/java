package com.nitesh.stayIn.service;

import java.util.List;

import com.nitesh.stayIn.entity.Amenities;

public interface AmenitiesService {

	List<Amenities> findAllAmenities();

	List<Amenities> findAllAmenitiesByStatus(String amenitiesStatus);

	List<Amenities> findAllActiveAmenities();

	Amenities findByAmenitiesName(String amenitiesName);

	Amenities saveOrUpdateAmenities(Amenities amenities);

	Amenities findByAmenitiesId(Integer amenitiesId);

}
