package com.nitesh.stayIn.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequest {

	private Integer userDetailsId;

	@NotBlank(message = "First name not be blank.")
	@NotNull(message = "First name must be required!")
	private String firstName;

	@NotBlank(message = "Last name not be blank.")
	@NotNull(message = "Last name must be required!")
	private String lastName;

	@NotBlank(message = "Address not be blank.")
	@NotNull(message = "Address must be required!")
	private String address;

	@NotBlank(message = "City not be blank.")
	@NotNull(message = "City must be required!")
	private String city;

	@NotBlank(message = "State not be blank.")
	@NotNull(message = "State must be required!")
	private String state;

	// @Pattern(regexp = "[0-9]{6}",message = "Enter valid pin code number.")

	private Integer pinCode;

	// @ValidContact(message = "Please enter valid contact!")
	@NotNull
	private Long mobileNo;

	@Email(message = "Invalid Email Address")
	private String emailId;

	// @Pattern(regexp = "[0-9]{12}",message = "Enter valid aadhar card number.")
	private Long aadharCardNo;

	@NotBlank(message = "Username not be blank.")
	@NotNull(message = "Username must be required!")
	private String username;

	@NotBlank(message = "Password not be blank.")
	@NotNull(message = "Password must be required!")
	private String password;

	@NotBlank(message = "User Type not be blank.")
	@NotNull(message = "User Type must be required!")
	private String userType;

	private String userStatus;

	public SignUpRequest() {
	}

	public SignUpRequest(Integer userDetailsId,
			@NotBlank(message = "First name not be blank.") @NotNull(message = "First name must be required!") String firstName,
			@NotBlank(message = "Last name not be blank.") @NotNull(message = "Last name must be required!") String lastName,
			@NotBlank(message = "Address not be blank.") @NotNull(message = "Address must be required!") String address,
			@NotBlank(message = "City not be blank.") @NotNull(message = "City must be required!") String city,
			@NotBlank(message = "State not be blank.") @NotNull(message = "State must be required!") String state,
			@Pattern(regexp = "[0-9]{6}", message = "Enter valid pin code number.") Integer pinCode,
			@NotBlank(message = "Contact No. not be blank.") @NotNull(message = "Contact No. must be required!") Long mobileNo,
			@Email(message = "Invalid Email Address") String emailId,
			@Pattern(regexp = "[0-9]{12}", message = "Enter valid aadhar card number.") Long aadharCardNo,
			@NotBlank(message = "Username not be blank.") @NotNull(message = "Username must be required!") String username,
			@NotBlank(message = "Password not be blank.") @NotNull(message = "Password must be required!") String password,
			@NotBlank(message = "User Type not be blank.") @NotNull(message = "User Type must be required!") String userType) {
		this.userDetailsId = userDetailsId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.aadharCardNo = aadharCardNo;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public SignUpRequest(
			@NotBlank(message = "First name not be blank.") @NotNull(message = "First name must be required!") String firstName,
			@NotBlank(message = "Last name not be blank.") @NotNull(message = "Last name must be required!") String lastName,
			@NotBlank(message = "Address not be blank.") @NotNull(message = "Address must be required!") String address,
			@NotBlank(message = "City not be blank.") @NotNull(message = "City must be required!") String city,
			@NotBlank(message = "State not be blank.") @NotNull(message = "State must be required!") String state,
			@Pattern(regexp = "[0-9]{6}", message = "Enter valid pin code number.") Integer pinCode,
			@NotBlank(message = "Contact No. not be blank.") @NotNull(message = "Contact No. must be required!") Long mobileNo,
			@Email(message = "Invalid Email Address") String emailId,
			@Pattern(regexp = "[0-9]{12}", message = "Enter valid aadhar card number.") Long aadharCardNo,
			@NotBlank(message = "Username not be blank.") @NotNull(message = "Username must be required!") String username,
			@NotBlank(message = "Password not be blank.") @NotNull(message = "Password must be required!") String password,
			@NotBlank(message = "User Type not be blank.") @NotNull(message = "User Type must be required!") String userType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.aadharCardNo = aadharCardNo;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public Integer getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Integer userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getAadharCardNo() {
		return aadharCardNo;
	}

	public void setAadharCardNo(Long aadharCardNo) {
		this.aadharCardNo = aadharCardNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "SignUpRequest [userDetailsId=" + userDetailsId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", pinCode=" + pinCode
				+ ", mobileNo=" + mobileNo + ", emailId=" + emailId + ", aadharCardNo=" + aadharCardNo + ", username="
				+ username + ", password=" + password + ", userType=" + userType + ", userStatus=" + userStatus + "]";
	}

}
