package com.nitesh.stayIn.service;

import java.util.Date;
import java.util.List;

import com.nitesh.stayIn.entity.RentDetails;
import com.nitesh.stayIn.entity.RentImage;
import com.nitesh.stayIn.entity.RentMaster;

public interface RentService {

	RentMaster saveOrUpdateRentMaster(RentMaster rentMaster);

	RentDetails saveOrUpdateRentDetails(RentDetails rentDetails);

	RentImage saveOrUpdateRentImages(RentImage rentImage);

	List<RentImage> saveAllRentImages(List<RentImage> rentImages);

//	List<RentImage> findAllRentImages();

	List<RentMaster> findAllRentDetailsOfUser(Integer userId);

	RentMaster findRentMasterByRentId(Integer rentId);

	List<RentMaster> findAllRentMasterByStatus(String status);

//	List<User> findAllUserWithVerificationPendingRentHouse();

	RentMaster findRentHouseByRentAndUserId(Integer userId, Integer rentId);

	RentDetails findRentDetailsByRentId(Integer rentId);

	RentImage findRentImageByUserIdAndRentId(Integer userId, Integer rentImageId);

	RentMaster findRentHouseByBookingDate(Integer rentId, Date fromDate, Date toDate);

	List<RentMaster> findAvailableRentHouseByBookingDate(Date fromDate, Date toDate);

	List<RentMaster> findRentBookingDetailsByUserId(Integer userId);

	List<RentMaster> findRentBookingDetailsByUserIdAndStatus(Integer userId, String status);

}
