package com.nitesh.stayIn.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "rentImage")
public class RentImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rentImageId;

	@Column(name = "rentImageFileName")
	private String rentImageFileName;

	@Column(name = "rentImageStatus")
	private String rentImageStatus;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "rentDetailsId", referencedColumnName = "rentDetailsId")
	@JsonBackReference
	private RentDetails rentDetails;

	public RentImage() {
	}

	public RentImage(String rentImageFileName, String rentImageStatus, RentDetails rentDetails) {
		this.rentImageFileName = rentImageFileName;
		this.rentImageStatus = rentImageStatus;
		this.rentDetails = rentDetails;
	}

	public Integer getRentImageId() {
		return rentImageId;
	}

	public void setRentImageId(Integer rentImageId) {
		this.rentImageId = rentImageId;
	}

	public String getRentImageFileName() {
		return rentImageFileName;
	}

	public void setRentImageFileName(String rentImageFileName) {
		this.rentImageFileName = rentImageFileName;
	}

	public String getRentImageStatus() {
		return rentImageStatus;
	}

	public void setRentImageStatus(String rentImageStatus) {
		this.rentImageStatus = rentImageStatus;
	}

	public RentDetails getRentDetails() {
		return rentDetails;
	}

	public void setRentDetails(RentDetails rentDetails) {
		this.rentDetails = rentDetails;
	}

	@Override
	public String toString() {
		return "RentImage [rentImageId=" + rentImageId + ", rentImageFileName=" + rentImageFileName
				+ ", rentImageStatus=" + rentImageStatus + ", rentDetails=" + rentDetails + "]";
	}

}
