package com.nitesh.stayIn.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.stayIn.entity.Bill;
import com.nitesh.stayIn.entity.Booking;
import com.nitesh.stayIn.entity.RentMaster;
import com.nitesh.stayIn.entity.User;
import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.request.BookingRentRequest;
import com.nitesh.stayIn.service.BillService;
import com.nitesh.stayIn.service.BookingService;
import com.nitesh.stayIn.service.RentService;
import com.nitesh.stayIn.service.UserService;
import com.nitesh.stayIn.view.View;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BookingController {

	@Autowired
	private UserService userService;

	@Autowired
	private RentService rentService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private BillService billService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private java.util.Date parseDate(String date) {
		try {
			return DATE_FORMAT.parse(date);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	// TODO: preAuthorized remain
	@PostMapping("/bookRent")
	@PreAuthorize("hasRole('ROLE_U')")
	@Operation(summary = "Booking rent.")
	public ResponseEntity<?> saveBooking(@Valid @RequestBody BookingRentRequest bookingRentRequest,
			BindingResult bindingResult) {
		System.out.println("Book Rent : : : " + bookingRentRequest);
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(400)
					.body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(), "Enter Valid Data!",
							System.currentTimeMillis(), bindingResult.getFieldError().getDefaultMessage()));
		}

		Date checkIn = parseDate(bookingRentRequest.getCheckInDate());
		Date checkOut = parseDate(bookingRentRequest.getCheckOutDate());

		boolean check = checkIn.after(new Date()) || DateUtils.isSameDay(checkIn, new Date());
		if (!check) {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Invalid check in date.it should be today or future!", System.currentTimeMillis(), null));
		}

		User findByUserId = userService.findByUserId(bookingRentRequest.getUserId());
		if (findByUserId == null) {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"User not found :" + bookingRentRequest.getUserId(), System.currentTimeMillis(), null));
		}

		RentMaster rentMaster = rentService.findRentMasterByRentId(bookingRentRequest.getRentId());
		if (rentMaster == null) {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Rent House not found!", System.currentTimeMillis(), null));
		}

		if (rentMaster.getRentDetails().getMaxPerson() > bookingRentRequest.getNumberOfPerson()) {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Invalid number of person!", System.currentTimeMillis(), null));
		}

		LocalDate dateBefore = LocalDate.parse(bookingRentRequest.getCheckInDate());
		LocalDate dateAfter = LocalDate.parse(bookingRentRequest.getCheckOutDate());

		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		noOfDaysBetween = noOfDaysBetween + 1;
		if (noOfDaysBetween < 1) {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(), "Invalid dates!",
					System.currentTimeMillis(), null));
		}

		// check room available on that date range
		RentMaster isRentAvailable = rentService.findRentHouseByBookingDate(rentMaster.getRentId(), checkIn, checkOut);
		if (isRentAvailable != null) {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Already occupied on this dates.", System.currentTimeMillis(), null));
		}
		Float total = (float) ((bookingRentRequest.getNumberOfPerson() * rentMaster.getRentDetails().getFixCharge())
				/ rentMaster.getRentDetails().getMaxPerson());
		if (total.equals(bookingRentRequest.getTotalAmount())) {
			Booking booking = new Booking(checkIn, checkOut, bookingRentRequest.getNumberOfPerson(), "A",
					bookingRentRequest.getComments(), bookingRentRequest.getRemarks(), findByUserId, rentMaster);
			booking = bookingService.saveOrUpdateBooking(booking);

			Bill bill = new Bill(java.sql.Date.valueOf(LocalDate.now()), total * noOfDaysBetween,
					bookingRentRequest.getBillDiscount(),
					(total * noOfDaysBetween) - bookingRentRequest.getBillDiscount(), "A", "Payment done!", booking);
			bill = billService.saveOrUpdateBill(bill);
			bill.setBooking(booking);
			return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED.value(), "Successfully book.",
					System.currentTimeMillis(), bill));

		} else {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Something want wrong in calculation.", System.currentTimeMillis(), null));
		}

	}

	@Operation(summary = "Find all available rent house by given date range.")
	@GetMapping("/findAllRentDetailsByBookingDates")
	@PreAuthorize("hasRole('ROLE_U')")
	public ResponseEntity<?> findAllRentDetailsByBookingDates(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) throws Exception {

		Date checkIn = parseDate(fromDate);
		Date checkOut = parseDate(toDate);

		boolean check = checkIn.after(new Date()) || DateUtils.isSameDay(checkIn, new Date());
		if (!check) {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Invalid check in date.it should be today or future!", System.currentTimeMillis(), null));
		}

		List<RentMaster> rentMasters = rentService.findAvailableRentHouseByBookingDate(checkIn, checkOut);
		if (rentMasters != null) {
//			return ResponseEntity.status(200).body(rentMasters);
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
			String response = objectMapper.writerWithView(View.UserDetailsWithBookingView.class)
					.writeValueAsString(rentMasters);
			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(),
							"Available rent house on " + checkIn + " to " + checkOut + ".", System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not available any rent house!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find all booking details by user id")
	@GetMapping("/findBookingDetailsByUserId")
	public ResponseEntity<?> findBookingDetailsByUserId(@RequestParam("userId") Integer userId) throws Exception {
//		List<Booking> user = bookingService.findBookingDetailsByUserId(userId);
		List<RentMaster> user = rentService.findRentBookingDetailsByUserId(userId);
		if (user != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.UserDetailsWithBookingView.class)
					.writeValueAsString(user);
			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(), "Booking details of user.",
							System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having any booking details!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find booking details by user id and booking status")
	@GetMapping("/findBookingDetailsByUserIdAndStatus")
	public ResponseEntity<?> findBookingDetailsByUserIdAndStatus(@RequestParam("userId") Integer userId,
			@RequestParam(name = "status", required = true, defaultValue = "A") String status) throws Exception {
//		List<Booking> bookings = bookingService.findBookingDetailsByUserIdAndStatus(userId, status);
		List<RentMaster> bookings = rentService.findRentBookingDetailsByUserIdAndStatus(userId, status);
		if (bookings != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.UserDetailsWithBookingView.class)
					.writeValueAsString(bookings);
			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(), "Getting booking details by user status.",
							System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));

		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having booking details.", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find booking details for rent owner using user id.")
	@GetMapping("/findBookingDetailsForRentOwnerByUserId")
	@PreAuthorize("hasAnyRole('ROLE_A','ROLE_O')")
	public ResponseEntity<?> findBookingDetailsForRentOwnerByUserId(@RequestParam("userId") Integer userId)
			throws Exception {
		List<Booking> bookings = bookingService.findBookingDetailsForRentOwnerByUserId(userId);
		if (bookings != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.BookingView.class).writeValueAsString(bookings);

			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(), "Getting booking details for rent owner.",
							System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having any bookings!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find booking details for rent owner using user id and booking status.")
	@GetMapping("/findBookingDetailsForRentOwnerByUserIdAndBookingStatus")
	@PreAuthorize("hasAnyRole('ROLE_A','ROLE_O')")
	public ResponseEntity<?> findBookingDetailsForRentOwnerByUserIdAndBookingStatus(
			@RequestParam("userId") Integer userId,
			@RequestParam(name = "status", required = true, defaultValue = "A") String status) throws Exception {

		List<Booking> bookings = bookingService.findBookingDetailsForRentOwnerByUserIdAndBookingStatus(userId, status);
		if (bookings != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.BookingView.class).writeValueAsString(bookings);

			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(),
							"Getting booking details for rent owner by booking status.", System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having any bookings!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find booking details for rent owner using user id and rent id.")
	@GetMapping("/findBookingDetailsForRentOwnerByUserIdAndRentId")
	@PreAuthorize("hasAnyRole('ROLE_A','ROLE_O')")
	public ResponseEntity<?> findBookingDetailsForRentOwnerByUserIdAndRentId(@RequestParam("userId") Integer userId,
			@RequestParam("rentId") Integer rentId) throws Exception {

		List<Booking> bookings = bookingService.findBookingDetailsForRentOwnerByUserIdAndRentId(userId, rentId);
		if (bookings != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.BookingView.class).writeValueAsString(bookings);

			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(),
							"Getting booking details for rent owner by rent id.", System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having any bookings!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find booking details for rent owner using user id,rent id and booking status.")
	@GetMapping("/findBookingDetailsForRentOwnerByUserIdAndRentIdAndBookingStatus")
	@PreAuthorize("hasAnyRole('ROLE_A','ROLE_O')")
	public ResponseEntity<?> findBookingDetailsForRentOwnerByUserIdAndRentIdAndBookingStatus(
			@RequestParam("userId") Integer userId, @RequestParam("rentId") Integer rentId,
			@RequestParam(name = "status", required = true, defaultValue = "A") String status) throws Exception {
		List<Booking> bookings = bookingService.findBookingDetailsForRentOwnerByUserIdAndRentIdAndBookingStatus(userId,
				rentId, status);
		if (bookings != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.BookingView.class).writeValueAsString(bookings);

			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"Getting booking details for rent owner by rent and booking status.", System.currentTimeMillis(),
					objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having any bookings!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find booking details by rent owner id and booking id.")
	@GetMapping("/findBookingDetailsByOwnerIdAndBookingId")
	public ResponseEntity<?> findBookingDetailsByOwnerIdAndBookingId(@RequestParam("bookingId") Integer bookingId,
			@RequestParam("userId") Integer userId) throws Exception {

		Booking booking = bookingService.findBookingDetailsByOwnerIdAndBookingId(bookingId, userId);
		if (booking != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.BookingView.class).writeValueAsString(booking);

			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(), "Booking details by booking id and rent owner id.",
							System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having any bookings!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Update booking status by booking id and rent owner id with remarks")
	@PutMapping("/updateBookingStatus")
	public ResponseEntity<?> updateBookingStatus(@RequestParam("bookingId") Integer bookingId,
			@RequestParam("userId") Integer userId, @RequestParam("status") String status,
			@RequestParam("remarks") String remarks) throws Exception {

		if (status.equals("A") || status.equals("C") || status.equals("D") || status.equals("O")
				|| status.equals("R")) {
			Booking booking = bookingService.findBookingDetailsByOwnerIdAndBookingId(bookingId, userId);
			if (booking != null) {
				booking.setBookingStatus(status);
				booking.setRemarks(remarks);

				Booking updatedBooking = bookingService.saveOrUpdateBooking(booking);
				final ObjectMapper objectMapper = new ObjectMapper();
				String response = objectMapper.writerWithView(View.BookingView.class)
						.writeValueAsString(updatedBooking);

				return ResponseEntity.status(200)
						.body(new SuccessResponse(HttpStatus.OK.value(),
								"Booking details by booking id and rent owner id.", System.currentTimeMillis(),
								objectMapper.getFactory().createParser(response).readValueAsTree()));
			} else {
				return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
						"Not having any bookings!", System.currentTimeMillis(), null));
			}
		} else {
			return ResponseEntity.status(400).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Invalid Status!", System.currentTimeMillis(), null));
		}
	}

}
