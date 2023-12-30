package com.nitesh.stayIn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nitesh.stayIn.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	@Query("from UserDetails u where u.user.userId = ?1")
	UserDetails findByUserId(int userId);

	Optional<UserDetails> findByEmailIdIgnoreCase(String emailId);

	Optional<UserDetails> findByAadharCardNo(Long aadharCardNo);
}
