package com.nitesh.stayIn.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nitesh.stayIn.entity.User;
import com.nitesh.stayIn.entity.UserDetails;
import com.nitesh.stayIn.request.SignUpRequest;

public interface UserService extends UserDetailsService {

	List<User> findAllUser();

	User findByUsername(String username);

	User saveUser(User user);

	User findByUserId(int userId);

	User createJwtToken(User user) throws Exception;

	List<User> findAllActiveUser();

	List<User> findAllActiveRentOwner();

	List<User> findAllActiveRentUser();

	List<User> findAllActiveRentOwner(String status);

	List<User> findAllUserDetails();

	UserDetails findAllUserDetailsByUserId(int userId);

	UserDetails findUserDetailsByUserDetailsId(int userDetails);

	UserDetails registerUserD(SignUpRequest user);

	UserDetails updateUserDetails(UserDetails userDetails);

	User findRentOwnerByUserId(int userId);

	UserDetails findByEmailId(String emailId);

	UserDetails findByAadharCardNo(Long aadharCardNo);

	List<User> findRentOwners();

	List<User> findAppUsers();

	List<User> findAllUserWithVerificationPendingRentHouse();

	List<User> findAllUserWithRentHouseByRentStatus(String status);
}
