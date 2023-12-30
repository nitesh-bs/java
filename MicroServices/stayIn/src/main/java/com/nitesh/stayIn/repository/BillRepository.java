package com.nitesh.stayIn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nitesh.stayIn.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {

	@Query("SELECT SUM(b.billAmount) as amount  FROM Bill b where b.booking.bookingStatus = 'D' and b.booking.rentMaster.user.userId = :userId")
	Float totalComplatedBookingAmountByRentOwnerId(@Param("userId") Integer userId);

}
