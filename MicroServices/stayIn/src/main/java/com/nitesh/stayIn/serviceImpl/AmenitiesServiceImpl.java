package com.nitesh.stayIn.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.stayIn.entity.Amenities;
import com.nitesh.stayIn.repository.AmenitiesRepository;
import com.nitesh.stayIn.service.AmenitiesService;

@Service
public class AmenitiesServiceImpl implements AmenitiesService {

	@Autowired
	private AmenitiesRepository amenitiesRepository;

	@Override
	public List<Amenities> findAllAmenities() {
		List<Amenities> amenities = amenitiesRepository.findAll();
		if (!amenities.isEmpty()) {
			return amenities;
		} else {
			return null;
		}
	}

	@Override
	public List<Amenities> findAllAmenitiesByStatus(String amenitiesStatus) {
		List<Amenities> amenities = amenitiesRepository.findAllAmenitiesByStatus(amenitiesStatus);
		if (!amenities.isEmpty()) {
			return amenities;
		} else {
			return null;
		}
	}

	@Override
	public List<Amenities> findAllActiveAmenities() {
		List<Amenities> amenities = amenitiesRepository.findAllActiveAmenities();
		if (!amenities.isEmpty()) {
			return amenities;
		} else {
			return null;
		}
	}

	@Override
	public Amenities findByAmenitiesName(String amenitiesName) {
		Optional<Amenities> findByAmenitiesName = amenitiesRepository.findByAmenitiesNameIgnoreCase(amenitiesName);
		if (findByAmenitiesName.isPresent()) {
			return findByAmenitiesName.get();
		} else {
			return null;
		}
	}

	@Override
	public Amenities saveOrUpdateAmenities(Amenities amenities) {
		Amenities savedAmenities = amenitiesRepository.save(amenities);
		return savedAmenities;
	}

	@Override
	public Amenities findByAmenitiesId(Integer amenitiesId) {
		Optional<Amenities> findByAmenitiesId = amenitiesRepository.findById(amenitiesId);
		if (findByAmenitiesId.isPresent()) {
			return findByAmenitiesId.get();
		} else {
			return null;
		}
	}

}
