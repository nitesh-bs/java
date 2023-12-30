package com.nitesh.stayIn.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class BookingRentRequest {

	private Integer BookingId;

	private Integer rentId;

	private Integer userId;

	@Positive(message = "Number of person must be greater or equal 1.")
	private Integer numberOfPerson;

	@NotBlank(message = "Check In not be blank.")
	@NotNull(message = "Check In must be required!")
//	@FutureOrPresent(message = "Invalid date, It should be as future or present date")
	private String checkInDate;

	@NotBlank(message = "Check Out not be blank.")
	@NotNull(message = "Check Out must be required!")
	private String checkOutDate;

	@Positive(message = "Total Amount must be positive.")
	private Float totalAmount;

	@PositiveOrZero(message = "Discount must be positive or zero.")
	private Float billDiscount;

	private String comments;

	private String remarks;

	public BookingRentRequest() {
	}

	public BookingRentRequest(Integer rentId, Integer userId,
			@Positive(message = "Number of person must be greater or equal 1.") Integer numberOfPerson,
			@NotBlank(message = "Check In not be blank.") @NotNull(message = "Check In must be required!") String checkInDate,
			@NotBlank(message = "Check Out not be blank.") @NotNull(message = "Check Out must be required!") String checkOutDate,
			@Positive(message = "Total Amount must be positive.") Float totalAmount,
			@PositiveOrZero(message = "Discount must be positive or zero.") Float billDiscount, String comments,
			String remarks) {
		this.rentId = rentId;
		this.userId = userId;
		this.numberOfPerson = numberOfPerson;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalAmount = totalAmount;
		this.billDiscount = billDiscount;
		this.comments = comments;
		this.remarks = remarks;
	}

	public Integer getBookingId() {
		return BookingId;
	}

	public void setBookingId(Integer bookingId) {
		BookingId = bookingId;
	}

	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNumberOfPerson() {
		return numberOfPerson;
	}

	public void setNumberOfPerson(Integer numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float getBillDiscount() {
		return billDiscount;
	}

	public void setBillDiscount(Float billDiscount) {
		this.billDiscount = billDiscount;
	}

	@Override
	public String toString() {
		return "BookingRentRequest [BookingId=" + BookingId + ", rentId=" + rentId + ", userId=" + userId
				+ ", numberOfPerson=" + numberOfPerson + ", checkInDate=" + checkInDate + ", checkOutDate="
				+ checkOutDate + ", totalAmount=" + totalAmount + ", billDiscount=" + billDiscount + ", billAmount="
				+ comments + ", remarks=" + remarks + "]";
	}

}
