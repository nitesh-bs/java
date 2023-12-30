package com.nitesh.stayIn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nitesh.stayIn.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("from Booking b where b.user.userId = :userId ")
	List<Booking> findBookingDetailsByUserId(@Param("userId") Integer userId);

	@Query("from Booking b where b.user.userId = :userId and b.bookingStatus = :status")
	List<Booking> findBookingDetailsByUserIdAndStatus(@Param("userId") Integer userId, @Param("status") String status);

	@Query("from Booking b where b.rentMaster.user.userId = :userId ")
	List<Booking> findBookingDetailsForRentOwnerByUserId(@Param("userId") Integer userId);

	@Query("from Booking b where b.rentMaster.user.userId = :userId and b.bookingStatus = :status")
	List<Booking> findBookingDetailsForRentOwnerByUserIdAndBookingStatus(@Param("userId") Integer userId,
			@Param("status") String status);

	@Query("from Booking b where b.rentMaster.user.userId = :userId and b.rentMaster.rentId = :rentId")
	List<Booking> findBookingDetailsForRentOwnerByUserIdAndRentId(@Param("userId") Integer userId,
			@Param("rentId") Integer rentId);

	@Query("from Booking b where b.rentMaster.user.userId = :userId and b.rentMaster.rentId = :rentId and b.bookingStatus = :status")
	List<Booking> findBookingDetailsForRentOwnerByUserIdAndRentIdAndBookingStatus(@Param("userId") Integer userId,
			@Param("rentId") Integer rentId, @Param("status") String status);

	@Query("from Booking b where b.bookingId = :bookingId and b.rentMaster.user.userId = :userId")
	Booking findBookingDetailsByOwnerIdAndBookingId(@Param("bookingId") Integer bookingId,
			@Param("userId") Integer userId);

	@Query("SELECT COUNT(*) as total from Booking b where b.rentMaster.user.userId = :userId and b.bookingStatus = 'D'")
	Integer totalComplatedBookingByRentOwnerId(@Param("userId") Integer userId);

}
