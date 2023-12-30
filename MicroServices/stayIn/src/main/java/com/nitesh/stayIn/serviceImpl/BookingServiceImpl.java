package com.nitesh.stayIn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.stayIn.entity.Booking;
import com.nitesh.stayIn.repository.BookingRepository;
import com.nitesh.stayIn.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public Booking saveOrUpdateBooking(Booking booking) {
		Booking saveOrUpdateBooking = bookingRepository.save(booking);
		return saveOrUpdateBooking;
	}

	@Override
	public List<Booking> findBookingDetailsByUserId(Integer userId) {
		List<Booking> booking = bookingRepository.findBookingDetailsByUserId(userId);
		if (!booking.isEmpty()) {
			return booking;
		} else {
			return null;
		}
	}

	@Override
	public List<Booking> findBookingDetailsByUserIdAndStatus(Integer userId, String status) {
		List<Booking> bookings = bookingRepository.findBookingDetailsByUserIdAndStatus(userId, status);
		if (!bookings.isEmpty()) {
			return bookings;
		} else {
			return null;
		}
	}

	@Override
	public List<Booking> findBookingDetailsForRentOwnerByUserId(Integer userId) {
		List<Booking> bookings = bookingRepository.findBookingDetailsForRentOwnerByUserId(userId);
		if (!bookings.isEmpty()) {
			return bookings;
		} else {
			return null;
		}
	}

	@Override
	public List<Booking> findBookingDetailsForRentOwnerByUserIdAndBookingStatus(Integer userId, String status) {
		List<Booking> bookings = bookingRepository.findBookingDetailsForRentOwnerByUserIdAndBookingStatus(userId,
				status);
		if (!bookings.isEmpty()) {
			return bookings;
		} else {
			return null;
		}
	}

	@Override
	public List<Booking> findBookingDetailsForRentOwnerByUserIdAndRentId(Integer userId, Integer rentId) {
		List<Booking> bookings = bookingRepository.findBookingDetailsForRentOwnerByUserIdAndRentId(userId, rentId);
		if (!bookings.isEmpty()) {
			return bookings;
		} else {
			return null;
		}
	}

	@Override
	public List<Booking> findBookingDetailsForRentOwnerByUserIdAndRentIdAndBookingStatus(Integer userId, Integer rentId,
			String status) {
		List<Booking> bookings = bookingRepository
				.findBookingDetailsForRentOwnerByUserIdAndRentIdAndBookingStatus(userId, rentId, status);
		if (!bookings.isEmpty()) {
			return bookings;
		} else {
			return null;
		}
	}

	@Override
	public Booking findBookingDetailsByOwnerIdAndBookingId(Integer bookingId, Integer userId) {
		Booking booking = bookingRepository.findBookingDetailsByOwnerIdAndBookingId(bookingId, userId);
		if (booking != null) {
			return booking;
		} else {
			return null;
		}
	}

}
