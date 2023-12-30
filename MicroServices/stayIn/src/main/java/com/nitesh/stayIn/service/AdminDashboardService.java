package com.nitesh.stayIn.service;

public interface AdminDashboardService {

	int totalRentOwner();

	int totalPendingVerificationOfRentOwner();

	int totalDisapprovedVerificationOfRentOwner();

	int totalUser();

	int totalPendingVerificationOfRentUser();

	int totalRentHouses();

	int totalPendingVerificationOfRentHouses();

	int totalDisapprovedVerificationOfRentHouses();

	int totalActiveRentHouses();

	int totalActiveRentOwner();

}
