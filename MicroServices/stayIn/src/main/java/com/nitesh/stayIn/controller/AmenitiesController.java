package com.nitesh.stayIn.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.stayIn.entity.Amenities;
import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.request.AmenitiesRequest;
import com.nitesh.stayIn.service.AmenitiesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AmenitiesController {

	@Autowired
	private AmenitiesService amenitiesService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Operation(summary = "Find All Amenities of all status. Only access by admin.")
	@GetMapping("/admin/findAllAmenities")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> findAllAmenities() {
		List<Amenities> amenities = amenitiesService.findAllAmenities();
		if (amenities != null) {
			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"Getting All Amenities Details", System.currentTimeMillis(), amenities));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having Amenities Details", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find amenities by amenities id. Only access by admin.")
	@GetMapping("/admin/findByAmenitiesId/{amenitiesId}")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> findByAmenitiesId(@PathVariable("amenitiesId") Optional<Integer> amenitiesId) {
		if (amenitiesId.isPresent()) {
			Amenities amenities = amenitiesService.findByAmenitiesId(amenitiesId.get());
			if (amenities != null) {
				return ResponseEntity.status(200)
						.body(new SuccessResponse(HttpStatus.OK.value(),
								"Getting amenities details by amenities Id - " + amenitiesId.get() + ".",
								System.currentTimeMillis(), amenities));
			} else {
				return ResponseEntity.status(404)
						.body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
								"Amenities Not Found! Amenities id is " + amenitiesId.get() + ".",
								System.currentTimeMillis(), null));
			}
		} else {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Amenities Id is required!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find All Amenities by status.default status is A. Only access by admin.")
	@GetMapping("/admin/findAmenitiesByAmenitiesStatus")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> findAmenitiesByAmenitiesStatus(
			@RequestParam(value = "status", required = false, defaultValue = "A") String status) {

		List<Amenities> amenities = amenitiesService.findAllAmenitiesByStatus(status);
		if (amenities != null) {
			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"Getting all amenities of given status", System.currentTimeMillis(), amenities));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having Amenities", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find Active Amenities.All can access this api.")
	@GetMapping("/findActiveAmenities")
	public ResponseEntity<?> findActiveAmenities() {
		List<Amenities> amenities = amenitiesService.findAllAmenitiesByStatus("A");
		if (amenities != null) {
			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"Getting Active Amenities.", System.currentTimeMillis(), amenities));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having amenities!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Save Amenities.")
	@PostMapping("/admin/saveAmenities")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> saveAmenities(@Valid @RequestBody AmenitiesRequest amenitiesRequest) {

		System.out.println("amenitiesRequest ::::::: " + amenitiesRequest);
		Amenities findAmenities = amenitiesService.findByAmenitiesName(amenitiesRequest.getAmenitiesName());
		if (findAmenities != null) {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Amenities Already Exists!", System.currentTimeMillis(), null));
		}
		Amenities amenities = new Amenities(amenitiesRequest.getAmenitiesName());
		amenities.setAmenitiesStatus("A");

		Amenities savedAmenities = amenitiesService.saveOrUpdateAmenities(amenities);
		return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED.value(),
				"Amenities Created Successfully.", System.currentTimeMillis(), savedAmenities));

	}

	@Operation(summary = "Update Amenities name.Only access by admin.")
	@PutMapping("/admin/updateAmenities")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> updateAmenitiesName(@Valid @RequestBody AmenitiesRequest amenitiesRequest) {
		Amenities amenities = amenitiesService.findByAmenitiesId(amenitiesRequest.getAmenitiesId());
		if (amenities != null) {
			// findByAmenitiesName
			if (!amenities.getAmenitiesName().equalsIgnoreCase(amenitiesRequest.getAmenitiesName())) {
				Amenities findAmenities = amenitiesService.findByAmenitiesName(amenitiesRequest.getAmenitiesName());
				if (findAmenities != null) {
					return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
							"Amenities Already Exists!", System.currentTimeMillis(), null));
				}
			}
			amenities.setAmenitiesName(amenitiesRequest.getAmenitiesName());
			Amenities updatedAmenities = amenitiesService.saveOrUpdateAmenities(amenities);
			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"Successfully updated amenities.", System.currentTimeMillis(), updatedAmenities));

		} else {
			return ResponseEntity.status(404)
					.body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
							"Amenities Not Found! Amenities Id - " + amenitiesRequest.getAmenitiesId(),
							System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Update amenities status(A/D). Only access by admin.")
	@PutMapping("/admin/updateAmenitiesStatus")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> updateAmenitiesStatus(@RequestBody AmenitiesRequest amenitiesRequest) {
		System.out.println("amenitiesRequest :::: " + amenitiesRequest);
		Amenities amenities = amenitiesService.findByAmenitiesId(amenitiesRequest.getAmenitiesId());
		if (amenities != null) {
			if (amenitiesRequest.getAmenitiesStatus() != null) {
				if (amenitiesRequest.getAmenitiesStatus().equals("A")
						|| amenitiesRequest.getAmenitiesStatus().equals("D")) {
					amenities.setAmenitiesStatus(amenitiesRequest.getAmenitiesStatus());
					Amenities updatedAmenities = amenitiesService.saveOrUpdateAmenities(amenities);
					return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
							"Successfully updated amenities status.", System.currentTimeMillis(), updatedAmenities));
				} else {
					return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
							"Invalid Amenities Status!", System.currentTimeMillis(), null));
				}
			} else {
				return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
						"Invalid Amenities Status!", System.currentTimeMillis(), null));
			}

		} else {
			return ResponseEntity.status(404)
					.body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
							"Amenities Not Found! Amenities id is " + amenitiesRequest.getAmenitiesId() + ".",
							System.currentTimeMillis(), null));
		}
	}
}
