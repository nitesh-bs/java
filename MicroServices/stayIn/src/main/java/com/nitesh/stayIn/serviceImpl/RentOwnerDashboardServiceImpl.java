package com.nitesh.stayIn.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.stayIn.repository.BillRepository;
import com.nitesh.stayIn.repository.BookingRepository;
import com.nitesh.stayIn.repository.RentMasterRepository;
import com.nitesh.stayIn.service.RentOwnerDashboardService;

@Service
public class RentOwnerDashboardServiceImpl implements RentOwnerDashboardService {

	@Autowired
	private RentMasterRepository rentMasterRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BillRepository billRepository;

	@Override
	public int totalRentHousesByRentOwnerId(Integer userId) {
		return rentMasterRepository.totalRentHousesByRentOwnerId(userId);
	}

	@Override
	public int totalPendingVerificationOfRentHousesByRentOwnerId(Integer userId) {
		return rentMasterRepository.totalPendingVerificationOfRentHousesByRentOwnerId(userId);
	}

	@Override
	public int totalDisapprovedVerificationOfRentHousesByRentOwnerId(Integer userId) {
		return rentMasterRepository.totalDisapprovedVerificationOfRentHousesByRentOwnerId(userId);
	}

	@Override
	public int totalActiveRentHousesByRentOwnerId(Integer userId) {
		return rentMasterRepository.totalActiveRentHousesByRentOwnerId(userId);
	}

	@Override
	public int totalComplatedBookingByRentOwnerId(Integer userId) {
		return bookingRepository.totalComplatedBookingByRentOwnerId(userId);
	}

	@Override
	public int totalComplatedBookingAmountByRentOwnerId(Integer userId) {
		Float amount = billRepository.totalComplatedBookingAmountByRentOwnerId(userId);
		if (amount != null) {
			return amount.intValue();
		} else {
			return 0;
		}
	}

}
