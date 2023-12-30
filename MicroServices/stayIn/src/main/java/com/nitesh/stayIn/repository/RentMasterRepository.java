package com.nitesh.stayIn.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nitesh.stayIn.entity.RentMaster;

public interface RentMasterRepository extends JpaRepository<RentMaster, Integer> {

	@Query("from RentMaster rm where rm.user.userId = ?1")
	List<RentMaster> findAllRentDetailsOfUser(Integer userId);

	@Query("from RentMaster rm where rm.rentStatus = :status ")
	List<RentMaster> findByRentStatus(@Param("status") String status);

	@Query("SELECT COUNT(*) from RentMaster")
	int totalRentHouses();

	@Query("SELECT COUNT(*) from RentMaster rm where rm.rentStatus = 'V'")
	int totalPendingVerificationOfRentHouses();

	@Query("SELECT COUNT(*) from RentMaster rm where rm.rentStatus = 'D'")
	int totalDisapprovedVerificationOfRentHouses();

	@Query("SELECT COUNT(*) from RentMaster rm where rm.rentStatus = 'A'")
	int totalActiveRentHouses();

	@Query("SELECT COUNT(*) from RentMaster rm where rm.user.userId = :userId")
	int totalRentHousesByRentOwnerId(@Param("userId") Integer userId);

	@Query("SELECT COUNT(*) from RentMaster rm where rm.rentStatus = 'V' and rm.user.userId = :userId")
	int totalPendingVerificationOfRentHousesByRentOwnerId(@Param("userId") Integer userId);

	@Query("SELECT COUNT(*) from RentMaster rm where rm.rentStatus = 'D' and rm.user.userId = :userId")
	int totalDisapprovedVerificationOfRentHousesByRentOwnerId(@Param("userId") Integer userId);

	@Query("SELECT COUNT(*) from RentMaster rm where rm.rentStatus = 'A' and rm.user.userId = :userId")
	int totalActiveRentHousesByRentOwnerId(@Param("userId") Integer userId);

//	@Query("SELECT COUNT(*) as total from RentMaster rm where rm.user.userId = :userId ")
//	@Query("SELECT COUNT(*) from RentMaster rm where rm.rentStatus = 'A' and rm.user.userId = :userId")
//	Integer totalComplatedBookingByRentOwnerId(@Param("userId") Integer userId);

//	@Query("SELECT DISTINCT r.user from RentMaster r  where r.user.userType = 'ROLE_O' and r.rentStatus = 'V'")
//	List<User> findVerificationPendingRentHouse();

	@Query("from RentMaster rm where rm.user.userId = :userId and rm.rentId = :rentId")
	RentMaster findRentHouseByRentAndUserId(@Param("userId") Integer userId, @Param("rentId") Integer rentId);

//	@Query("SELECT DISTINCT rm FROM RentMaster rm JOIN FETCH rm.bookings as b where b.bookingStatus='A' and rm.rentId = :rentId and b.fromDate > :fromDate and b.toDate < :toDate")
	@Query("SELECT DISTINCT rm FROM RentMaster rm JOIN FETCH rm.bookings as b where b.bookingStatus='A' and rm.rentId = :rentId and ((b.fromDate between :fromDate and :toDate) or (b.toDate  between :fromDate and :toDate) OR \n"
			+ "(b.fromDate  <= :fromDate AND b.toDate >= :toDate) )")
	RentMaster findRentHouseByBookingDate(@Param("rentId") Integer rentId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Query("SELECT DISTINCT rm FROM RentMaster rm JOIN FETCH rm.bookings as b where b.bookingStatus='A' and ((b.fromDate between :fromDate and :toDate) or (b.toDate  between :fromDate and :toDate) OR \n"
			+ "(b.fromDate  <= :fromDate AND b.toDate >= :toDate) )")
	List<RentMaster> findNotAvailableRentHouseByBookingDate(@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Query("SELECT DISTINCT rm FROM RentMaster rm where rm.rentStatus = 'A' and rm.rentId not in (:ids)")
	List<RentMaster> findAvailableRentHouseByBookingDate(@Param("ids") List<Integer> ids);

	@Query("from RentMaster rm JOIN FETCH rm.bookings as b where b.user.userId = :userId ")
	List<RentMaster> findRentBookingDetailsByUserId(@Param("userId") Integer userId);

	@Query("from RentMaster rm JOIN FETCH rm.bookings as b  where b.user.userId = :userId and b.bookingStatus = :status")
	List<RentMaster> findRentBookingDetailsByUserIdAndStatus(@Param("userId") Integer userId,
			@Param("status") String status);

}
