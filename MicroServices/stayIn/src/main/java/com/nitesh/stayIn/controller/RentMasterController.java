package com.nitesh.stayIn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.stayIn.entity.Amenities;
import com.nitesh.stayIn.entity.Furnishing;
import com.nitesh.stayIn.entity.RentDetails;
import com.nitesh.stayIn.entity.RentImage;
import com.nitesh.stayIn.entity.RentMaster;
import com.nitesh.stayIn.entity.RentType;
import com.nitesh.stayIn.entity.User;
import com.nitesh.stayIn.exception.BadRequestException;
import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.request.RentSaveRequest;
import com.nitesh.stayIn.request.RentUpdateRequest;
import com.nitesh.stayIn.service.AmenitiesService;
import com.nitesh.stayIn.service.RentService;
import com.nitesh.stayIn.service.UserService;
import com.nitesh.stayIn.serviceImpl.FileStorageService;
import com.nitesh.stayIn.view.View;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RentMasterController {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private UserService userService;

	@Autowired
	private RentService rentService;

	@Autowired
	private AmenitiesService amenitiesService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// TODO - rentMaster rentRemark Pending

	@Operation(summary = "Get all user rent house details which is under verification.Only access by admin.")
	@GetMapping("/admin/PedningVerificationOfRentHouse")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> findAllUserWithVerificationPendingRentHouse() throws Exception {
		List<User> users = userService.findAllUserWithVerificationPendingRentHouse();
		if (users != null) {

			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.RentMasterWithUserView.class).writeValueAsString(users);

			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(), "Pending verification of rent house.",
							System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having pending verification of rent house.", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Get all user rent house details by rent status.Only access by admin.")
	@GetMapping("/admin/findRentHouseOfUserByRentStatus")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> findRentHouseOfUserByUserStatus(
			@RequestParam(name = "status", required = false, defaultValue = "A") String status) throws Exception {
		List<User> users = userService.findAllUserWithRentHouseByRentStatus(status);
		if (users != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.RentMasterWithUserView.class).writeValueAsString(users);

			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(), "Getting rent house by given status.",
							System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having rent houses!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Save New Rent House.Only access by rent owner.")
	@PreAuthorize("hasRole('ROLE_O')")
	@PostMapping(path = "/saveRent")
	public ResponseEntity<?> saveRent(@Valid @ModelAttribute RentSaveRequest rentSaveRequest,
			BindingResult bindingResult) {
		System.out.println(rentSaveRequest);
		Furnishing furnishing = null;
		try {
			furnishing = Furnishing.valueOf(rentSaveRequest.getFurnishing());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Furnishing not found :" + rentSaveRequest.getFurnishing(), System.currentTimeMillis(), null));
		}

		RentType rentType = null;
		try {
			rentType = RentType.valueOf(rentSaveRequest.getRentType());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Rent Type not found :" + rentSaveRequest.getRentType(), System.currentTimeMillis(), null));
		}

		User findByUserId = userService.findByUserId(rentSaveRequest.getUserId());
		if (findByUserId == null) {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"User not found :" + rentSaveRequest.getUserId(), System.currentTimeMillis(), null));
		}

		List<MultipartFile> files = rentSaveRequest.getFile();
		System.out.println("file ::: " + files.size());

		List<RentImage> rentImages = new ArrayList<>();

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(400)
					.body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(), "Enter valid Data!",
							System.currentTimeMillis(), bindingResult.getFieldError().getDefaultMessage()));
		} else {

			if (files.isEmpty() || files.size() == 0) {
				return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
						"Need to upload file!", System.currentTimeMillis(), null));
			} else {
				int count = 0;
				for (MultipartFile multipartFile : files) {
					if (multipartFile.isEmpty()) {
						return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
								"Please select a file to Upload!", System.currentTimeMillis(), null));
					} else {
						String fileExtention = fileStorageService.getFileExtension(multipartFile.getOriginalFilename());

						System.out.print("file extension : " + fileExtention);

						if (fileExtention.equals("jpeg") || fileExtention.equals("jpg")
								|| fileExtention.equals("png")) {

							count++;

						} else {
							return ResponseEntity.status(400)
									.body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
											"Invalid File type : " + multipartFile.getOriginalFilename(),
											System.currentTimeMillis(), null));
						}
					}
				}

				Set<String> strAmenities = rentSaveRequest.getAmenities();
				Set<Amenities> amenities = new HashSet<>();

				if (strAmenities == null) {
					return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
							"Amenities not found ", System.currentTimeMillis(), null));
				} else {
					for (String amenitiesName : strAmenities) {

						Amenities getAmenitie = amenitiesService.findByAmenitiesName(amenitiesName);
						if (getAmenitie != null) {
							amenities.add(getAmenitie);
						} else {
							return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
									"Amenities not found : " + amenitiesName, System.currentTimeMillis(), null));
						}
					}

				}

				if (count == files.size()) {

					if (strAmenities.size() == amenities.size()) {

						RentMaster rentMaster = new RentMaster(rentSaveRequest.getRentAddress(),
								rentSaveRequest.getRentCity(), rentSaveRequest.getRentState(),
								rentSaveRequest.getRentContactNo(), "V", findByUserId);
						rentMaster = rentService.saveOrUpdateRentMaster(rentMaster);

						RentDetails rentDetails = new RentDetails(rentType, rentSaveRequest.getBuiltUpArea(),
								rentSaveRequest.getAgeOfProperty(), rentSaveRequest.getPropertyOnFloor(),
								rentSaveRequest.getTotalFloor(), rentSaveRequest.getBhk(),
								rentSaveRequest.getBathrooms(), rentSaveRequest.getFixCharge(),
								rentSaveRequest.getMaxPerson(), rentSaveRequest.isPowerBackUp(), furnishing,
								rentSaveRequest.getAboutThisProperty(), rentSaveRequest.getTermAndConditions(),
								(float) 0.0, rentMaster, amenities);
						rentDetails = rentService.saveOrUpdateRentDetails(rentDetails);

						for (MultipartFile multipartFile : files) {
							String fileName = fileStorageService.storeFile(multipartFile);
							rentImages.add(new RentImage(fileName, "A", rentDetails));
						}
						if (rentImages.size() == 1) {
							RentImage rentImageSaved = rentService.saveOrUpdateRentImages(rentImages.get(0));
							return ResponseEntity.status(201)
									.body(new SuccessResponse(HttpStatus.CREATED.value(),
											"Successfully created Rent Details.Need to approve by Admin.",
											System.currentTimeMillis(), null));
						} else {
							List<RentImage> rentImagesAll = rentService.saveAllRentImages(rentImages);
							return ResponseEntity.status(201)
									.body(new SuccessResponse(HttpStatus.CREATED.value(),
											"Successfully created Rent Details.Need to approve by Admin.",
											System.currentTimeMillis(), null));
						}
					}
				} else {
					return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
							"Error in File Upload! Please,Upload Valid Files. ", System.currentTimeMillis(), null));
				}
			}
		}

		return null;
	}

	@SuppressWarnings("unused")
	@Operation(summary = "Update rent house details.")
	@PutMapping("/updateRent")
	@PreAuthorize("hasRole('ROLE_O')")
	public ResponseEntity<?> updateRent(@Valid @ModelAttribute RentUpdateRequest rentUpdateRequest,
			BindingResult bindingResult) {
		System.out.println("Rent Update Request ::: " + rentUpdateRequest);

		User findByUserId = userService.findByUserId(rentUpdateRequest.getUserId());
		if (findByUserId == null) {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"User not found :" + rentUpdateRequest.getUserId(), System.currentTimeMillis(), null));
		}

		RentMaster rentMasters = rentService.findRentHouseByRentAndUserId(rentUpdateRequest.getUserId(),
				rentUpdateRequest.getRentId());
		if (rentMasters == null) {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Invalid rent id of user!", System.currentTimeMillis(), null));
		}

		RentDetails details = rentService.findRentDetailsByRentId(rentMasters.getRentId());
		if (details == null) {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Unable to get rent details!", System.currentTimeMillis(), null));
		}

		Furnishing furnishing = null;
		try {
			furnishing = Furnishing.valueOf(rentUpdateRequest.getFurnishing());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Furnishing not found :" + rentUpdateRequest.getFurnishing(), System.currentTimeMillis(), null));
		}

		RentType rentType = null;
		try {
			rentType = RentType.valueOf(rentUpdateRequest.getRentType());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Rent Type not found :" + rentUpdateRequest.getRentType(), System.currentTimeMillis(), null));
		}

		List<MultipartFile> files = rentUpdateRequest.getFile();

		List<RentImage> rentImages = new ArrayList<>();

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(400)
					.body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(), "Enter valid Data!",
							System.currentTimeMillis(), bindingResult.getFieldError().getDefaultMessage()));
		} else {

			if (files != null) {
				if (files.isEmpty() || files.size() == 0) {
					return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
							"Need to upload file!", System.currentTimeMillis(), null));
				} else {
					int count = 0;
					for (MultipartFile multipartFile : files) {
						if (multipartFile.isEmpty()) {
							return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
									"Please select a file to Upload!", System.currentTimeMillis(), null));
						} else {
							String fileExtention = fileStorageService
									.getFileExtension(multipartFile.getOriginalFilename());

							System.out.print("file extension : " + fileExtention);

							if (fileExtention.equals("jpeg") || fileExtention.equals("jpg")
									|| fileExtention.equals("png")) {

								count++;

							} else {
								return ResponseEntity.status(400)
										.body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
												"Invalid File type : " + multipartFile.getOriginalFilename(),
												System.currentTimeMillis(), null));
							}
						}
					}

					Set<String> strAmenities = rentUpdateRequest.getAmenities();
					Set<Amenities> amenities = new HashSet<>();

					if (strAmenities == null) {
						return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
								"Amenities not found ", System.currentTimeMillis(), null));
					} else {
						for (String amenitiesName : strAmenities) {

							Amenities getAmenitie = amenitiesService.findByAmenitiesName(amenitiesName);
							if (getAmenitie != null) {
								amenities.add(getAmenitie);
							} else {
								return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
										"Amenities not found : " + amenitiesName, System.currentTimeMillis(), null));
							}
						}

					}

					if (count == files.size()) {

						if (strAmenities.size() == amenities.size()) {
							RentMaster rentUpdateMaster = new RentMaster(rentMasters.getRentId(),
									rentUpdateRequest.getRentAddress(), rentUpdateRequest.getRentCity(),
									rentUpdateRequest.getRentState(), rentUpdateRequest.getRentContactNo(), "V",
									findByUserId);
							rentUpdateMaster = rentService.saveOrUpdateRentMaster(rentUpdateMaster);

							RentDetails rentDetails = new RentDetails(details.getRentDetailsId(), rentType,
									rentUpdateRequest.getBuiltUpArea(), rentUpdateRequest.getAgeOfProperty(),
									rentUpdateRequest.getPropertyOnFloor(), rentUpdateRequest.getTotalFloor(),
									rentUpdateRequest.getBhk(), rentUpdateRequest.getBathrooms(),
									rentUpdateRequest.getFixCharge(), rentUpdateRequest.getMaxPerson(),
									rentUpdateRequest.isPowerBackUp(), furnishing,
									rentUpdateRequest.getAboutThisProperty(), rentUpdateRequest.getTermAndConditions(),
									(float) 0.0, rentUpdateMaster, amenities);
							rentDetails = rentService.saveOrUpdateRentDetails(rentDetails);

							for (MultipartFile multipartFile : files) {
								String fileName = fileStorageService.storeFile(multipartFile);
								rentImages.add(new RentImage(fileName, "A", rentDetails));
							}
							if (rentImages.size() == 1) {
								RentImage rentImageSaved = rentService.saveOrUpdateRentImages(rentImages.get(0));
								return ResponseEntity.status(201)
										.body(new SuccessResponse(HttpStatus.CREATED.value(),
												"Successfully created Rent Details.Need to approve by Admin.",
												System.currentTimeMillis(), null));
							} else {
								List<RentImage> rentImagesAll = rentService.saveAllRentImages(rentImages);
								return ResponseEntity.status(201)
										.body(new SuccessResponse(HttpStatus.CREATED.value(),
												"Successfully created Rent Details.Need to approve by Admin.",
												System.currentTimeMillis(), null));
							}
						}
					} else {
						return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
								"Error in File Upload! Please,Upload Valid Files. ", System.currentTimeMillis(), null));
					}
				}

			} else {

				Set<String> strAmenities = rentUpdateRequest.getAmenities();
				Set<Amenities> amenities = new HashSet<>();

				if (strAmenities == null) {
					return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
							"Amenities not found ", System.currentTimeMillis(), null));
				} else {
					for (String amenitiesName : strAmenities) {

						Amenities getAmenitie = amenitiesService.findByAmenitiesName(amenitiesName);
						if (getAmenitie != null) {
							amenities.add(getAmenitie);
						} else {
							return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
									"Amenities not found : " + amenitiesName, System.currentTimeMillis(), null));
						}
					}

				}

				if (strAmenities.size() == amenities.size()) {

					RentMaster rentUpdateMaster = new RentMaster(rentMasters.getRentId(),
							rentUpdateRequest.getRentAddress(), rentUpdateRequest.getRentCity(),
							rentUpdateRequest.getRentState(), rentUpdateRequest.getRentContactNo(), "V", findByUserId);
					rentUpdateMaster = rentService.saveOrUpdateRentMaster(rentUpdateMaster);

					RentDetails rentDetails = new RentDetails(details.getRentDetailsId(), rentType,
							rentUpdateRequest.getBuiltUpArea(), rentUpdateRequest.getAgeOfProperty(),
							rentUpdateRequest.getPropertyOnFloor(), rentUpdateRequest.getTotalFloor(),
							rentUpdateRequest.getBhk(), rentUpdateRequest.getBathrooms(),
							rentUpdateRequest.getFixCharge(), rentUpdateRequest.getMaxPerson(),
							rentUpdateRequest.isPowerBackUp(), furnishing, rentUpdateRequest.getAboutThisProperty(),
							rentUpdateRequest.getTermAndConditions(), (float) 0.0, rentUpdateMaster, amenities);
					rentDetails = rentService.saveOrUpdateRentDetails(rentDetails);

					return ResponseEntity.status(200)
							.body(new SuccessResponse(HttpStatus.OK.value(),
									"Successfully updated Rent Details.Details goes for approve to admin.",
									System.currentTimeMillis(), null));

				}

			}
		}

		return null;

	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			throw new BadRequestException("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

//	@GetMapping("/findAllRentImage")
//	public ResponseEntity<?> findAllRentImage() {
//		List<RentImage> rentImages = rentService.findAllRentImages();
//		if (rentImages != null) {
//			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(), "Get All Rent Images",
//					System.currentTimeMillis(), rentImages));
//		} else {
//			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
//					"Rent Image Not Found", System.currentTimeMillis(), null));
//		}
//	}

	// TODO :: remain to give preAuthorized Role
	@Operation(summary = "Update rent image status.")
	@PutMapping("/updateRentImageStatus")
	public ResponseEntity<?> updateRentImageStatus(@RequestParam("userId") Integer userId,
			@RequestParam("rentImageId") Integer rentImageId) {
		RentImage rentImage = rentService.findRentImageByUserIdAndRentId(userId, rentImageId);
//		RentImage rentImage =  rentService.findById(5);
		if (rentImage != null) {
			if (rentImage.getRentImageStatus().equals("A")) {
				rentImage.setRentImageStatus("D");
			} else {
				rentImage.setRentImageStatus("A");
			}
			RentImage updatedRentImage = rentService.saveOrUpdateRentImages(rentImage);
			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"Successfully updated status.", System.currentTimeMillis(), null));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Rent Image Not Found", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find all rent houses by user id.Admin and Rent owner can access this api.")
	@GetMapping("/findAllRentHouseOfUserByUserId")
	@PreAuthorize("hasAnyRole('ROLE_A','ROLE_O')")
	public ResponseEntity<?> findAllRentDetailsOfUserID(@RequestParam(name = "userId") Optional<Integer> userId) {
		if (userId.isPresent()) {

			List<RentMaster> rentMasters = rentService.findAllRentDetailsOfUser(userId.get());

			if (rentMasters != null) {
//				ObjectMapper mapper = new ObjectMapper();
//				SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
//	                    .serializeAllExcept("user");
//	            FilterProvider filters = new SimpleFilterProvider()
//	                    .addFilter("rentMasterFilter", filter);
//
//	            String dtoAsString = mapper.writer(filters).writeValueAsString(rentDetails);
//
//	            JsonFactory factory = mapper.getFactory();
//	            // create parser by using the createParser() method
//	            JsonParser parser = factory.createParser(dtoAsString);
//	            // create JsonNode from parser
//	            JsonNode node = mapper.readTree(parser);
				return ResponseEntity.status(200)
						.body(new SuccessResponse(HttpStatus.OK.value(),
								"Getting all rent house of user - " + userId.get() + ".", System.currentTimeMillis(),
								rentMasters));
			} else {
				return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
						"Not found any rent house!", System.currentTimeMillis(), null));
			}
		} else {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(), "Need to userId.",
					System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Update rent status by rent id.status be like A/V/D.Only access by admin.")
	@PutMapping("/updateRentStatus")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> updateRentStatus(@RequestParam("rentId") Integer rentId,
			@RequestParam("rentStatus") String rentStatus) {

		RentMaster rentMaster = rentService.findRentMasterByRentId(rentId);
		if (rentMaster != null) {
			if (!rentStatus.isBlank() || !rentStatus.isEmpty()) {
				if (rentStatus.equals("A") || rentStatus.equals("V") || rentStatus.equals("D")) {
					rentMaster.setRentStatus(rentStatus);
					RentMaster updatedRentMaster = rentService.saveOrUpdateRentMaster(rentMaster);
					return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
							"Rent Status Changed Successfully!", System.currentTimeMillis(), updatedRentMaster));
				} else {
					return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
							"Rent Status is Invalid!", System.currentTimeMillis(), null));
				}
			} else {
				return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
						"Rent Status is Invalid!", System.currentTimeMillis(), null));
			}
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Rent Details Not Found!", System.currentTimeMillis(), null));
		}

	}

	@Operation(summary = "Get All Rent House without user details by status of rent.Only access by admin.")
	@PreAuthorize("hasRole('ROLE_A')")
	@GetMapping("/findAllRentMasterByStatus")
	public ResponseEntity<?> findRentMasterByRentStatus(
			@RequestParam(value = "status", required = false, defaultValue = "A") String status) {

		List<RentMaster> rentMasters = rentService.findAllRentMasterByStatus(status);
		if (rentMasters != null) {
			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"Getting all rent house by rent status.", System.currentTimeMillis(), rentMasters));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not Found rent houses!", System.currentTimeMillis(), null));
		}
	}

}
