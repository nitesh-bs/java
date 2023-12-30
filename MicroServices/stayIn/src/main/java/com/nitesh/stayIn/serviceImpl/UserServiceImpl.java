package com.nitesh.stayIn.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nitesh.stayIn.entity.User;
import com.nitesh.stayIn.repository.UserDetailsRepository;
import com.nitesh.stayIn.repository.UserMasterRepository;
import com.nitesh.stayIn.request.SignUpRequest;
import com.nitesh.stayIn.service.UserService;
import com.nitesh.stayIn.utils.JwtUtils;

//import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMasterRepository userMasterRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public List<User> findAllUser() {
		return userMasterRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		Optional<User> user = userMasterRepository.findByUsernameIgnoreCase(username);
		if (user.isPresent()) {
			return user.get();
		} else {
			// throw new UsernameNotFoundException("Username not found!");
			return null;
		}
	}

	@Override
	public User saveUser(User user) {

		User registeredUser = userMasterRepository.save(user);
		return registeredUser;
	}

	@Override
	public User createJwtToken(User user) throws Exception {

		System.out.println("Remove this : " + user);
		authentication(user.getUsername(), user.getPassword());

		final UserDetails userDetails = loadUserByUsername(user.getUsername());

		User userResponse = userMasterRepository.findByUsernameIgnoreCase(user.getUsername()).get();

		if (userResponse.getUserStatus().equals("A")) {
			String generateToken = jwtUtils.generateToken(userDetails);
			userResponse.setUserToken(generateToken);
		} else {
			userResponse = null;
		}

		return userResponse;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMasterRepository.findByUsernameIgnoreCase(username).get();
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username and Password!!!!!!!!!!!!!!!");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user));

	}

	public Collection<GrantedAuthority> mapRolesToAuthorities(User auths) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(auths.getUserType()));
		return grantedAuthorities;
	}

	private void authentication(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("User is disable");
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Bad Credentials from User");
		} catch (Exception e) {
			throw new RuntimeException("Invalid Username or Password!");
		}
	}

	@Override
	public List<User> findAllActiveUser() {
		List<User> user = userMasterRepository.findAllActiveUser();
		if (!user.isEmpty()) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAllActiveRentOwner() {
		List<User> user = userMasterRepository.findAllActiveRentOwner();
		if (!user.isEmpty()) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAllActiveRentUser() {
		List<User> user = userMasterRepository.findAllActiveRentUser();
		if (!user.isEmpty()) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAllActiveRentOwner(String status) {
		List<User> user = userMasterRepository.findAllActiveRentOwner(status);
		if (!user.isEmpty()) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAllUserDetails() {
//		List<com.nitesh.stayIn.entity.UserDetails> user = userDetailsRepository.findAll();
		List<User> user = userMasterRepository.findAllUserDetails();
		if (!user.isEmpty()) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public com.nitesh.stayIn.entity.UserDetails findAllUserDetailsByUserId(int userId) {
		com.nitesh.stayIn.entity.UserDetails userDetails = userDetailsRepository.findByUserId(userId);
		if (userDetails != null) {
			return userDetails;
		} else {
			return null;
		}
	}

	@Override
	public com.nitesh.stayIn.entity.UserDetails registerUserD(SignUpRequest user) {

		User signUpUser = new User(user.getUsername(), user.getPassword(), user.getUserType(), user.getUserStatus());

		User registeredUser = userMasterRepository.save(signUpUser);
		com.nitesh.stayIn.entity.UserDetails userDetails = new com.nitesh.stayIn.entity.UserDetails(user.getFirstName(),
				user.getLastName(), user.getAddress(), user.getCity(), user.getState(), user.getPinCode(),
				user.getMobileNo(), user.getEmailId(), user.getAadharCardNo(), registeredUser);

		com.nitesh.stayIn.entity.UserDetails registeredUserDetails = userDetailsRepository.save(userDetails);
		return registeredUserDetails;

	}

	@Override
	public User findByUserId(int userId) {
		Optional<User> users = userMasterRepository.findById(userId);
		if (users.isPresent()) {
			return users.get();
		} else {
			return null;
		}
	}

	@Override
	public com.nitesh.stayIn.entity.UserDetails findUserDetailsByUserDetailsId(int userDetails) {
		Optional<com.nitesh.stayIn.entity.UserDetails> findById = userDetailsRepository.findById(userDetails);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			return null;
		}
	}

	@Override
	public com.nitesh.stayIn.entity.UserDetails updateUserDetails(com.nitesh.stayIn.entity.UserDetails userDetails) {
		com.nitesh.stayIn.entity.UserDetails updatedUserDetails = userDetailsRepository.save(userDetails);
		return updatedUserDetails;
	}

	@Override
	public User findRentOwnerByUserId(int userId) {
		User user = userMasterRepository.findRentOwnerByUserId(userId);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public com.nitesh.stayIn.entity.UserDetails findByEmailId(String emailId) {
		Optional<com.nitesh.stayIn.entity.UserDetails> user = userDetailsRepository.findByEmailIdIgnoreCase(emailId);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	public com.nitesh.stayIn.entity.UserDetails findByAadharCardNo(Long aadharCardNo) {
		Optional<com.nitesh.stayIn.entity.UserDetails> user = userDetailsRepository.findByAadharCardNo(aadharCardNo);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	public List<User> findRentOwners() {
		List<User> user = userMasterRepository.findAllRentOwners();
		if (!user.isEmpty()) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAppUsers() {
		List<User> user = userMasterRepository.findAllAppUser();
		if (!user.isEmpty()) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAllUserWithVerificationPendingRentHouse() {
		List<User> user = userMasterRepository.findAllUserWithVerificationPendingRentHouse();
		if (!user.isEmpty()) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAllUserWithRentHouseByRentStatus(String status) {
		List<User> users = userMasterRepository.findAllUSerWithRentHouseByRentStatus(status);
		if (!users.isEmpty()) {
			return users;
		} else {
			return null;
		}
	}

}
