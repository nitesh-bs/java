package com.nitesh.stayIn.service;

import java.util.List;

import com.nitesh.stayIn.entity.Booking;

public interface BookingService {
	Booking saveOrUpdateBooking(Booking booking);

	List<Booking> findBookingDetailsByUserId(Integer userId);

	List<Booking> findBookingDetailsByUserIdAndStatus(Integer userId, String status);

	List<Booking> findBookingDetailsForRentOwnerByUserId(Integer userId);

	List<Booking> findBookingDetailsForRentOwnerByUserIdAndBookingStatus(Integer userId, String status);

	List<Booking> findBookingDetailsForRentOwnerByUserIdAndRentId(Integer userId, Integer rentId);

	List<Booking> findBookingDetailsForRentOwnerByUserIdAndRentIdAndBookingStatus(Integer userId, Integer rentId,
			String status);

	Booking findBookingDetailsByOwnerIdAndBookingId(Integer bookingId, Integer userId);
}
