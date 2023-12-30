package com.nitesh.stayIn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nitesh.stayIn.entity.RentDetails;

public interface RentDetailsRepository extends JpaRepository<RentDetails, Integer> {

	@Query("from RentDetails rd where rd.rentMaster.rentId = :rentId")
	RentDetails findRentDetailsByRentId(@Param("rentId") Integer rentId);

}
