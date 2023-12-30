package com.nitesh.stayIn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nitesh.stayIn.entity.Amenities;

public interface AmenitiesRepository extends JpaRepository<Amenities, Integer> {

	Optional<Amenities> findByAmenitiesNameIgnoreCase(String amenitiesName);

	@Query("from Amenities a where a.amenitiesStatus = 'A'")
	List<Amenities> findAllActiveAmenities();

	@Query("from Amenities a where a.amenitiesStatus = :status")
	List<Amenities> findAllAmenitiesByStatus(@Param("status") String status);

}
