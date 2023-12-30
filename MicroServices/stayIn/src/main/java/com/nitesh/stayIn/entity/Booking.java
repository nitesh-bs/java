package com.nitesh.stayIn.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.nitesh.stayIn.view.View;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;

	@Column(name = "fromDate")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Calcutta")
	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Column(name = "toDate")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Calcutta")
	@Temporal(TemporalType.DATE)
	private Date toDate;

	@Column(name = "numberOfPerson")
	private Integer numberOfPerson;

	// Pending,Rejected,Accepted,Occupied,Cancelled,Done
	@Column(name = "bookingStatus")
	private String bookingStatus;

	@Column(name = "comments")
	private String comments;

	@Column(name = "remarks")
	private String remarks;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@JsonManagedReference
	@JsonView(View.BookingView.class)
	private User user;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "rentId", referencedColumnName = "rentId")
	@JsonBackReference
	private RentMaster rentMaster;

	@OneToOne(mappedBy = "booking")
	@JsonManagedReference
	private Bill bill;

	public Booking() {
	}

	public Booking(Integer bookingId, Date fromDate, Date toDate, String bookingStatus, String comments, String remarks,
			User user) {
		this.bookingId = bookingId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.bookingStatus = bookingStatus;
		this.comments = comments;
		this.remarks = remarks;
		this.user = user;
	}

	public Booking(Date fromDate, Date toDate, Integer numberOfPerson, String bookingStatus, String comments,
			String remarks, User user, RentMaster rentMaster) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.numberOfPerson = numberOfPerson;
		this.bookingStatus = bookingStatus;
		this.comments = comments;
		this.remarks = remarks;
		this.user = user;
		this.rentMaster = rentMaster;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getNumberOfPerson() {
		return numberOfPerson;
	}

	public void setNumberOfPerson(Integer numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}

	public RentMaster getRentMaster() {
		return rentMaster;
	}

	public void setRentMaster(RentMaster rentMaster) {
		this.rentMaster = rentMaster;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", fromDate=" + fromDate + ", toDate=" + toDate + ", numberOfPerson="
				+ numberOfPerson + ", bookingStatus=" + bookingStatus + ", comments=" + comments + ", remarks="
				+ remarks + ", user=" + user + ", rentMaster=" + rentMaster + "]";
	}

}
