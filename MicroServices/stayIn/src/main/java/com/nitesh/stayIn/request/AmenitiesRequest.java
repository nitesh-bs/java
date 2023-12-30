package com.nitesh.stayIn.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AmenitiesRequest {

	private Integer amenitiesId;

	@NotBlank(message = "Amenities name not be black.")
	@NotNull(message = "Amenities name must be required!")
	private String amenitiesName;

	private String amenitiesStatus;

	public AmenitiesRequest() {
	}

	public AmenitiesRequest(String amenitiesName, String amenitiesStatus) {
		this.amenitiesName = amenitiesName;
		this.amenitiesStatus = amenitiesStatus;
	}

	public AmenitiesRequest(String amenitiesName) {
		this.amenitiesName = amenitiesName;
	}

	public AmenitiesRequest(Integer amenitiesId, String amenitiesName, String amenitiesStatus) {
		this.amenitiesId = amenitiesId;
		this.amenitiesName = amenitiesName;
		this.amenitiesStatus = amenitiesStatus;
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
