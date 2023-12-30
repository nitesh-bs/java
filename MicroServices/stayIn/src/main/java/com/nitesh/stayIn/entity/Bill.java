package com.nitesh.stayIn.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billId;

	@Column(name = "billDate")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Calcutta")
//	@JsonFormat(shape = Shape.STRING)
//	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date billDate;

	@Column(name = "totalAmount")
	private Float totalAmount;

	@Column(name = "billDiscount")
	private Float billDiscount;

	@Column(name = "billAmount")
	private Float billAmount;

	@Column(name = "billStatus")
	private String billStatus;

	@Column(name = "billRemarks")
	private String billRemarks;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "bookingId", referencedColumnName = "bookingId")
	@JsonBackReference
	private Booking booking;

	public Bill() {
	}

	public Bill(Date billDate, Float totalAmount, Float billDiscount, Float billAmount, String billStatus,
			String billRemarks, Booking booking) {
		this.billDate = billDate;
		this.totalAmount = totalAmount;
		this.billDiscount = billDiscount;
		this.billAmount = billAmount;
		this.billStatus = billStatus;
		this.billRemarks = billRemarks;
		this.booking = booking;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
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

	public Float getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Float billAmount) {
		this.billAmount = billAmount;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getBillRemarks() {
		return billRemarks;
	}

	public void setBillRemarks(String billRemarks) {
		this.billRemarks = billRemarks;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", billDate=" + billDate + ", totalAmount=" + totalAmount + ", billDiscount="
				+ billDiscount + ", billAmount=" + billAmount + ", billStatus=" + billStatus + ", billRemarks="
				+ billRemarks + ", booking=" + booking + "]";
	}

}
