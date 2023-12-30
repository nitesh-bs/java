package com.nitesh.stayIn.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "rentDetails")
public class RentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rentDetailsId;

	@Enumerated(EnumType.STRING)
	private RentType rentType;

	@Column(name = "builtUpArea")
	private Float builtUpArea;

	@Column(name = "ageOfProperty")
	private int ageOfProperty;

	@Column(name = "propertyOnFloor")
	private int propertyOnFloor;

	@Column(name = "totalFloor")
	private int totalFloor;

	@Column(name = "bhk")
	private int bhk;

	@Column(name = "bathrooms")
	private int bathrooms;

	@Column(name = "fixCharge")
	private int fixCharge;

	@Column(name = "maxPerson")
	private int maxPerson;

	@Column(name = "powerBackUp")
	private boolean powerBackUp;

	@Enumerated(EnumType.STRING)
	private Furnishing furnishing;

	@Column(name = "aboutThisProperty")
	private String aboutThisProperty;

	@Column(name = "termAndConditions")
	private String termAndConditions;

	@Column(name = "rating")
	private Float rating;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "rentId", referencedColumnName = "rentId")
	@JsonBackReference
	private RentMaster rentMaster;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rentAmenities", joinColumns = @JoinColumn(name = "rentDetailsId"), inverseJoinColumns = @JoinColumn(name = "amenitiesId"))
	private Set<Amenities> amenities = new HashSet();

	@OneToMany(mappedBy = "rentDetails")
	@JsonManagedReference
	private List<RentImage> rentImage;

	public RentDetails() {
	}

	public RentDetails(RentType rentType, Float builtUpArea, int ageOfProperty, int propertyOnFloor, int totalFloor,
			int bhk, int bathrooms, int fixCharge, int maxPerson, boolean powerBackUp, Furnishing furnishing,
			String aboutThisProperty, String termAndConditions, Float rating, RentMaster rentMaster,
			Set<Amenities> amenities) {
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
		this.rating = rating;
		this.rentMaster = rentMaster;
		this.amenities = amenities;
	}

	public RentDetails(Integer rentDetailsId, RentType rentType, Float builtUpArea, int ageOfProperty,
			int propertyOnFloor, int totalFloor, int bhk, int bathrooms, int fixCharge, int maxPerson,
			boolean powerBackUp, Furnishing furnishing, String aboutThisProperty, String termAndConditions,
			Float rating, RentMaster rentMaster, Set<Amenities> amenities) {
		this.rentDetailsId = rentDetailsId;
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
		this.rating = rating;
		this.rentMaster = rentMaster;
		this.amenities = amenities;
	}

	public Integer getRentDetailsId() {
		return rentDetailsId;
	}

	public void setRentDetailsId(Integer rentDetailsId) {
		this.rentDetailsId = rentDetailsId;
	}

	public RentType getRentType() {
		return rentType;
	}

	public void setRentType(RentType rentType) {
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

	public Furnishing getFurnishing() {
		return furnishing;
	}

	public void setFurnishing(Furnishing furnishing) {
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

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public RentMaster getRentMaster() {
		return rentMaster;
	}

	public void setRentMaster(RentMaster rentMaster) {
		this.rentMaster = rentMaster;
	}

	public Set<Amenities> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<Amenities> amenities) {
		this.amenities = amenities;
	}

	public List<RentImage> getRentImage() {
		return rentImage;
	}

	public void setRentImage(List<RentImage> rentImage) {
		this.rentImage = rentImage;
	}

	@Override
	public String toString() {
		return "RentDetails [rentDetailsId=" + rentDetailsId + ", rentType=" + rentType + ", builtUpArea=" + builtUpArea
				+ ", ageOfProperty=" + ageOfProperty + ", propertyOnFloor=" + propertyOnFloor + ", totalFloor="
				+ totalFloor + ", bhk=" + bhk + ", bathrooms=" + bathrooms + ", fixCharge=" + fixCharge
				+ ", powerBackUp=" + powerBackUp + ", furnishing=" + furnishing + ", aboutThisProperty="
				+ aboutThisProperty + ", termAndConditions=" + termAndConditions + ", rating=" + rating
				+ ", rentMaster=" + rentMaster + ", amenities=" + amenities + ", rentImage=" + rentImage + "]";
	}

}
