package com.nitesh.stayIn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "amenities")
public class Amenities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer amenitiesId;

	@Column(name = "amenitiesName")
	private String amenitiesName;

	@Column(name = "amenitiesStatus")
	private String amenitiesStatus;

	public Amenities() {
	}

	public Amenities(String amenitiesName, String amenitiesStatus) {
		this.amenitiesName = amenitiesName;
		this.amenitiesStatus = amenitiesStatus;
	}

	public Amenities(String amenitiesName) {
		this.amenitiesName = amenitiesName;
	}

	public Integer getAmenitiesId() {
		return amenitiesId;
	}

	public void setAmenitiesId(Integer amenitiesId) {
		this.amenitiesId = amenitiesId;
	}

	public String getAmenitiesName() {
		return amenitiesName;
	}

	public void setAmenitiesName(String amenitiesName) {
		this.amenitiesName = amenitiesName;
	}

	public String getAmenitiesStatus() {
		return amenitiesStatus;
	}

	public void setAmenitiesStatus(String amenitiesStatus) {
		this.amenitiesStatus = amenitiesStatus;
	}

	@Override
	public String toString() {
		return "Amenities [amenitiesId=" + amenitiesId + ", amenitiesName=" + amenitiesName + ", amenitiesStatus="
				+ amenitiesStatus + "]";
	}

}
