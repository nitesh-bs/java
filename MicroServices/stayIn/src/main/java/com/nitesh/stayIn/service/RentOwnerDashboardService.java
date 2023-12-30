package com.nitesh.stayIn.service;

public interface RentOwnerDashboardService {

	int totalRentHousesByRentOwnerId(Integer userId);

	int totalPendingVerificationOfRentHousesByRentOwnerId(Integer userId);

	int totalDisapprovedVerificationOfRentHousesByRentOwnerId(Integer userId);

	int totalActiveRentHousesByRentOwnerId(Integer userId);

	int totalComplatedBookingByRentOwnerId(Integer userId);

	int totalComplatedBookingAmountByRentOwnerId(Integer userId);
}
