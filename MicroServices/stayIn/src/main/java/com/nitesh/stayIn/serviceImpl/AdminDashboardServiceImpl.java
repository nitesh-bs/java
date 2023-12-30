package com.nitesh.stayIn.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.stayIn.repository.RentMasterRepository;
import com.nitesh.stayIn.repository.UserMasterRepository;
import com.nitesh.stayIn.service.AdminDashboardService;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

	@Autowired
	private UserMasterRepository userMasterRepository;

	@Autowired
	private RentMasterRepository rentMasterRepository;

	@Override
	public int totalRentOwner() {
		return userMasterRepository.totalRentOwner();
	}

	@Override
	public int totalPendingVerificationOfRentOwner() {
		return userMasterRepository.totalPendingVerificationOfRentOwner();
	}

	@Override
	public int totalDisapprovedVerificationOfRentOwner() {
		return userMasterRepository.totalDisapprovedVerificationOfRentOwner();
	}

	@Override
	public int totalUser() {
		return userMasterRepository.totalUser();
	}

	@Override
	public int totalActiveRentOwner() {
		return userMasterRepository.totalActiveRentOwner();
	}

	@Override
	public int totalPendingVerificationOfRentUser() {
		return userMasterRepository.totalPendingVerificationOfRentUser();
	}

	@Override
	public int totalRentHouses() {
		return rentMasterRepository.totalRentHouses();
	}

	@Override
	public int totalPendingVerificationOfRentHouses() {
		return rentMasterRepository.totalPendingVerificationOfRentHouses();
	}

	@Override
	public int totalDisapprovedVerificationOfRentHouses() {
		return rentMasterRepository.totalDisapprovedVerificationOfRentHouses();
	}

	@Override
	public int totalActiveRentHouses() {
		return rentMasterRepository.totalActiveRentHouses();
	}

}
