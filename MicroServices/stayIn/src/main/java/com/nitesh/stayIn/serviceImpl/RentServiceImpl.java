package com.nitesh.stayIn.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.stayIn.entity.RentDetails;
import com.nitesh.stayIn.entity.RentImage;
import com.nitesh.stayIn.entity.RentMaster;
import com.nitesh.stayIn.repository.RentDetailsRepository;
import com.nitesh.stayIn.repository.RentImageRepository;
import com.nitesh.stayIn.repository.RentMasterRepository;
import com.nitesh.stayIn.service.RentService;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private RentMasterRepository rentMasterRepository;

	@Autowired
	private RentDetailsRepository rentDetailsRepository;

	@Autowired
	private RentImageRepository rentImageRepository;

	@Override
	public RentMaster saveOrUpdateRentMaster(RentMaster rentMaster) {
		RentMaster saveOrUpdateRentMaster = rentMasterRepository.save(rentMaster);
		return saveOrUpdateRentMaster;
	}

	@Override
	public RentDetails saveOrUpdateRentDetails(RentDetails rentDetails) {
		RentDetails saveOrUpdateRentDetails = rentDetailsRepository.save(rentDetails);
		return saveOrUpdateRentDetails;
	}

	@Override
	public RentImage saveOrUpdateRentImages(RentImage rentImage) {
		RentImage saveOrUpdateRentImage = rentImageRepository.save(rentImage);
		return saveOrUpdateRentImage;
	}

	@Override
	public List<RentImage> saveAllRentImages(List<RentImage> rentImages) {
		List<RentImage> saveAll = rentImageRepository.saveAll(rentImages);
		return saveAll;
	}

//	@Override
//	public List<RentImage> findAllRentImages() {
//		List<RentImage> rentImages = rentImageRepository.findAll();
//		if (!rentImages.isEmpty()) {
//			return rentImages;
//		} else {
//			return null;
//		}
//	}

	@Override
	public List<RentMaster> findAllRentDetailsOfUser(Integer userId) {
		List<RentMaster> rentMasters = rentMasterRepository.findAllRentDetailsOfUser(userId);
		if (!rentMasters.isEmpty()) {
			return rentMasters;
		} else {
			return null;
		}
	}

	@Override
	public RentMaster findRentMasterByRentId(Integer rentId) {
		Optional<RentMaster> rentMaster = rentMasterRepository.findById(rentId);
		if (rentMaster.isPresent()) {
			return rentMaster.get();
		} else {
			return null;
		}
	}

	@Override
	public List<RentMaster> findAllRentMasterByStatus(String status) {
		List<RentMaster> rentMasters = rentMasterRepository.findByRentStatus(status);
		if (!rentMasters.isEmpty()) {
			return rentMasters;
		} else {
			return null;
		}
	}

	@Override
	public RentMaster findRentHouseByRentAndUserId(Integer userId, Integer rentId) {
		RentMaster rentMasters = rentMasterRepository.findRentHouseByRentAndUserId(userId, rentId);
		if (rentMasters != null) {
			return rentMasters;
		} else {
			return null;
		}
	}

	@Override
	public RentDetails findRentDetailsByRentId(Integer rentId) {
		RentDetails rentDetails = rentDetailsRepository.findRentDetailsByRentId(rentId);
		if (rentDetails != null) {
			return rentDetails;
		} else {
			return null;
		}
	}

	@Override
	public RentImage findRentImageByUserIdAndRentId(Integer userId, Integer rentImageId) {
		RentImage rentImage = rentImageRepository.findRentImageByUserIdAndRentId(rentImageId, userId);
		if (rentImage != null) {
			return rentImage;
		} else {
			return null;
		}
	}

	@Override
	public RentMaster findRentHouseByBookingDate(Integer rentId, Date fromDate, Date toDate) {
		RentMaster rentMaster = rentMasterRepository.findRentHouseByBookingDate(rentId, fromDate, toDate);
		if (rentMaster != null) {
			return rentMaster;
		} else {
			return null;
		}
	}

	@Override
	public List<RentMaster> findAvailableRentHouseByBookingDate(Date fromDate, Date toDate) {

		List<Integer> rentIds = rentMasterRepository.findNotAvailableRentHouseByBookingDate(fromDate, toDate).stream()
				.map(RentMaster::getRentId).collect(Collectors.toList());
		if (!rentIds.isEmpty()) {
			List<RentMaster> rentMasters = rentMasterRepository.findAvailableRentHouseByBookingDate(rentIds);
			if (!rentMasters.isEmpty()) {
				return rentMasters;
			} else {
				return null;
			}
		} else {
			return rentMasterRepository.findByRentStatus("A");
		}

	}

	@Override
	public List<RentMaster> findRentBookingDetailsByUserId(Integer userId) {
		List<RentMaster> rentMasters = rentMasterRepository.findRentBookingDetailsByUserId(userId);
		if (!rentMasters.isEmpty()) {
			return rentMasters;
		} else {
			return null;
		}
	}

	@Override
	public List<RentMaster> findRentBookingDetailsByUserIdAndStatus(Integer userId, String status) {
		List<RentMaster> rentMasters = rentMasterRepository.findRentBookingDetailsByUserIdAndStatus(userId, status);
		if (!rentMasters.isEmpty()) {
			return rentMasters;
		} else {
			return null;
		}
	}

//	@Override
//	public RentImage findById(Integer rentImageId) {
//		Optional<RentImage> rentImages = rentImageRepository.findById(rentImageId);
//		if (rentImages.isPresent()) {
//			return rentImages.get();
//		} else {
//			return null;
//		}
//	}

//	@Override
//	public List<User> findAllUserWithVerificationPendingRentHouse() {
//		List<User> user = rentMasterRepository.findVerificationPendingRentHouse();
//		if (!user.isEmpty()) {
//			return user;
//		} else {
//			return null;
//		}
//	}

}
