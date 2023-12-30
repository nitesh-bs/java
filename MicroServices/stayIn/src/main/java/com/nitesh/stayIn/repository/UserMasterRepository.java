package com.nitesh.stayIn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nitesh.stayIn.entity.User;

public interface UserMasterRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsernameIgnoreCase(String username);

	Optional<User> findByUserStatus(String userStatus);

	@Query("from User u where u.userStatus = 'A' and u.userType = 'ROLE_O' and u.userId = :userId")
	User findRentOwnerByUserId(@Param("userId") Integer userId);

	@Query("from User u where u.userStatus = 'A'")
	List<User> findAllActiveUser();

	@Query("from User u where u.userStatus = 'A' and u.userId = :userId")
	User findActiveUserByUserId(@Param("userId") Integer userId);

	@Query("from User u where u.userType = 'ROLE_O' or u.userType = 'ROLE_U'")
	List<User> findAllUserDetails();

	@Query("from User u where u.userStatus = 'A' and u.userType = 'ROLE_O'")
	List<User> findAllActiveRentOwner();

	@Query("from User u where u.userStatus = 'A' and u.userType = 'ROLE_U'")
	List<User> findAllActiveRentUser();

	@Query("from User u where u.userStatus = :status and u.userType = 'ROLE_O'")
	List<User> findAllActiveRentOwner(@Param("status") String status);

	@Query("SELECT COUNT(*) from User u where u.userType = 'ROLE_O'")
	int totalRentOwner();

	@Query("SELECT COUNT(*) from User u where u.userType = 'ROLE_O' and u.userStatus = 'V'")
	int totalPendingVerificationOfRentOwner();

	@Query("SELECT COUNT(*) from User u where u.userType = 'ROLE_O' and u.userStatus = 'D'")
	int totalDisapprovedVerificationOfRentOwner();

	@Query("SELECT COUNT(*) from User u where u.userType = 'ROLE_U'")
	int totalUser();

	@Query("SELECT COUNT(*) from User u where u.userType = 'ROLE_U' and u.userStatus = 'V'")
	int totalPendingVerificationOfRentUser();

	@Query("SELECT COUNT(*) from User u where u.userType = 'ROLE_O' and u.userStatus = 'A'")
	int totalActiveRentOwner();

	@Query("from User u where u.userType = 'ROLE_O'")
	List<User> findAllRentOwners();

	@Query("from User u where u.userType = 'ROLE_U' ")
	List<User> findAllAppUser();

	@Query("SELECT DISTINCT u FROM User u JOIN FETCH u.rentMaster as rm where u.userType = 'ROLE_O' and rm.rentStatus = 'V'")
	List<User> findAllUserWithVerificationPendingRentHouse();

	@Query("SELECT DISTINCT u FROM User u JOIN FETCH u.rentMaster as rm where u.userType = 'ROLE_O' and rm.rentStatus = :status")
	List<User> findAllUSerWithRentHouseByRentStatus(@Param("status") String status);

}
