package com.nitesh.stayIn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nitesh.stayIn.entity.RentImage;

public interface RentImageRepository extends JpaRepository<RentImage, Integer> {

	// @Query("from RentImage ri where ri.rentImageId = :rentImageId and
	// ri.rentDetails.rentMaster.user.userId = :userId ")
//	@Query("SELECT DISTINCT ri FROM RentImage ri JOIN FETCH ri.rentDetails as rd JOIN FETCH rd.rentMaster as rm JOIN FETCH rm.user as u where u.userId = :userId and ri.rentImageId = :rentImageId")
//	@Query("SELECT DISTINCT ri FROM User u JOIN FETCH u.rentMaster as rm JOIN FETCH rm.rentDetails as rd JOIN FETCH rd.rentImage as ri where u.userId = :userId and ri.rentImageId = :rentImageId")

	@Query("from RentImage ri where ri.rentImageId = :rentImageId and ri.rentDetails.rentMaster.user.userId = :userId ")
	RentImage findRentImageByUserIdAndRentId(@Param("rentImageId") Integer rentImageId,
			@Param("userId") Integer userId);
}
