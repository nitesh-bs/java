package com.nitesh.stayIn.request;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.web.multipart.MultipartFile;

import com.nitesh.stayIn.validation.ValidContact;

public class RentSaveRequest {

	@NotBlank(message = "Address not be blank.")
	@NotNull(message = "Address must be required!")
	private String rentAddress;

	private Integer userId;

	@NotBlank(message = "City not be blank.")
	@NotNull(message = "City must be required!")
	private String rentCity;

	@NotBlank(message = "State not be blank.")
	@NotNull(message = "State must be required!")
	private String rentState;

	@NotBlank(message = "ContactNo not be blank.")
	@NotNull(message = "ContactNo must be required!")
	@ValidContact(message = "Please enter valid contact!")
	private String rentContactNo;

	@NotBlank(message = "Rent Type not be blank.")
	@NotNull(message = "Rent Type must be required!")
	private String rentType;

	@Positive(message = "Build Up Area must be have positive value")
	private Float builtUpArea;

	@Min(value = 1, message = "Minimum value of age of property is 1")
	@NotNull(message = "Age of Property can not be null")
	@Positive(message = "Minimum value of age of property is 1")
	private int ageOfProperty;

	@PositiveOrZero(message = "Property On Floor is either 0 or up")
	private int propertyOnFloor;

	@Positive(message = "Total Floor must be 1 or above")
	private int totalFloor;

	@Positive(message = "Minimum value of BHK is 1")
	private int bhk;

	@PositiveOrZero(message = "Property On Floor is either 0 or up")
	private int bathrooms;

	@Positive(message = "Minimum value of fixCharge is 1")
	private int fixCharge;

	@Positive(message = "Minimum value of max person capacity is 1.")
	private int maxPerson;

//	@Pattern(regexp = "^(true|false)$", message = "restartable field allowed input: true or false")
	private boolean powerBackUp;

	@NotBlank(message = "Furnishing not be blank.")
	@NotNull(message = "Furnishing must be required!")
	private String furnishing;

	@NotBlank(message = "Properties Details not be blank.")
	@NotNull(message = "Properties Details must be required!")
	private String aboutThisProperty;

	@NotBlank(message = "Term & Condition not be blank.")
	@NotNull(message = "Term & Condition must be required!")
	private String termAndConditions;

	private Set<String> amenities;

	private List<MultipartFile> file;

	public RentSaveRequest() {
	}

	public RentSaveRequest(String rentAddress, Integer userId, String rentCity, String rentState, String rentContactNo,
			String rentType, Float builtUpArea, int ageOfProperty, int propertyOnFloor, int totalFloor, int bhk,
			int bathrooms, int fixCharge, int maxPerson, boolean powerBackUp, String furnishing,
			String aboutThisProperty, String termAndConditions, Set<String> amenities, List<MultipartFile> file) {
		this.rentAddress = rentAddress;
		this.userId = userId;
		this.rentCity = rentCity;
		this.rentState = rentState;
		this.rentContactNo = rentContactNo;
		this.rentType = rentType;
		this.builtUpArea = builtUpArea;
		this.ageOfProperty = ageOfProperty;
		this.propertyOnFloor = propertyOnFloor;
		this.totalFloor = totalFloor;
		this.bhk = bhk;
		this.bathrooms = bathrooms;
		this.fixCharge = fixCharge;
		this.maxPerson = maxPerson;
		this.powerBackUp = powerBackUp;
		this.furnishing = furnishing;
		this.aboutThisProperty = aboutThisProperty;
		this.termAndConditions = termAndConditions;
		this.amenities = amenities;
		this.file = file;
	}

	public String getRentAddress() {
		return rentAddress;
	}

	public void setRentAddress(String rentAddress) {
		this.rentAddress = rentAddress;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRentCity() {
		return rentCity;
	}

	public void setRentCity(String rentCity) {
		this.rentCity = rentCity;
	}

	public String getRentState() {
		return rentState;
	}

	public void setRentState(String rentState) {
		this.rentState = rentState;
	}

	public String getRentContactNo() {
		return rentContactNo;
	}

	public void setRentContactNo(String rentContactNo) {
		this.rentContactNo = rentContactNo;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public Float getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(Float builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public int getAgeOfProperty() {
		return ageOfProperty;
	}

	public void setAgeOfProperty(int ageOfProperty) {
		this.ageOfProperty = ageOfProperty;
	}

	public int getPropertyOnFloor() {
		return propertyOnFloor;
	}

	public void setPropertyOnFloor(int propertyOnFloor) {
		this.propertyOnFloor = propertyOnFloor;
	}

	public int getTotalFloor() {
		return totalFloor;
	}

	public void setTotalFloor(int totalFloor) {
		this.totalFloor = totalFloor;
	}

	public int getBhk() {
		return bhk;
	}

	public void setBhk(int bhk) {
		this.bhk = bhk;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getFixCharge() {
		return fixCharge;
	}

	public void setFixCharge(int fixCharge) {
		this.fixCharge = fixCharge;
	}

	public int getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}

	public boolean isPowerBackUp() {
		return powerBackUp;
	}

	public void setPowerBackUp(boolean powerBackUp) {
		this.powerBackUp = powerBackUp;
	}

	public String getFurnishing() {
		return furnishing;
	}

	public void setFurnishing(String furnishing) {
		this.furnishing = furnishing;
	}

	public String getAboutThisProperty() {
		return aboutThisProperty;
	}

	public void setAboutThisProperty(String aboutThisProperty) {
		this.aboutThisProperty = aboutThisProperty;
	}

	public String getTermAndConditions() {
		return termAndConditions;
	}

	public void setTermAndConditions(String termAndConditions) {
		this.termAndConditions = termAndConditions;
	}

	public Set<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<String> amenities) {
		this.amenities = amenities;
	}

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "RentSaveRequest [rentAddress=" + rentAddress + ", userId=" + userId + ", rentCity=" + rentCity
				+ ", rentState=" + rentState + ", rentContactNo=" + rentContactNo + ", rentType=" + rentType
				+ ", builtUpArea=" + builtUpArea + ", ageOfProperty=" + ageOfProperty + ", propertyOnFloor="
				+ propertyOnFloor + ", totalFloor=" + totalFloor + ", bhk=" + bhk + ", bathrooms=" + bathrooms
				+ ", fixCharge=" + fixCharge + ", powerBackUp=" + powerBackUp + ", furnishing=" + furnishing
				+ ", aboutThisProperty=" + aboutThisProperty + ", termAndConditions=" + termAndConditions
				+ ", amenities=" + amenities + ", file=" + file + "]";
	}

}
