package com.nitesh.stayIn.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "rentMaster")
//@JsonFilter("rentMasterFilter")
public class RentMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rentId")
	private Integer rentId;

	@Column(name = "rentAddress")
	private String rentAddress;

	@Column(name = "rentCity")
	private String rentCity;

	@Column(name = "rentState")
	private String rentState;

	@Column(name = "rentContactNo")
	private String rentContactNo;

	@Column(name = "rentStatus")
	private String rentStatus;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@JsonBackReference
	private User user;

	@OneToOne(mappedBy = "rentMaster")
	@JsonManagedReference
	private RentDetails rentDetails;

	@OneToMany(mappedBy = "rentMaster")
	@JsonManagedReference
	private List<Booking> bookings;

	@Transient
	@JsonInclude(value = Include.NON_NULL)
	private String rentRemark;

	public RentMaster() {

	}

	public RentMaster(String rentAddress, String rentCity, String rentState, String rentContactNo, String rentStatus,
			User user) {
		this.rentAddress = rentAddress;
		this.rentCity = rentCity;
		this.rentState = rentState;
		this.rentContactNo = rentContactNo;
		this.rentStatus = rentStatus;
		this.user = user;
	}

	public RentMaster(Integer rentId, String rentAddress, String rentCity, String rentState, String rentContactNo,
			String rentStatus, User user) {
		this.rentId = rentId;
		this.rentAddress = rentAddress;
		this.rentCity = rentCity;
		this.rentState = rentState;
		this.rentContactNo = rentContactNo;
		this.rentStatus = rentStatus;
		this.user = user;
	}

	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public String getRentAddress() {
		return rentAddress;
	}

	public void setRentAddress(String rentAddress) {
		this.rentAddress = rentAddress;
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

	public String getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(String rentStatus) {
		this.rentStatus = rentStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRentRemark() {
		return rentRemark;
	}

	public void setRentRemark(String rentRemark) {
		this.rentRemark = rentRemark;
	}

	public RentDetails getRentDetails() {
		return rentDetails;
	}

	public void setRentDetails(RentDetails rentDetails) {
		this.rentDetails = rentDetails;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "RentMaster [rentId=" + rentId + ", rentAddress=" + rentAddress + ", rentCity=" + rentCity
				+ ", rentState=" + rentState + ", rentContactNo=" + rentContactNo + ", rentStatus=" + rentStatus
				+ ", user=" + user + "]";
	}

}
