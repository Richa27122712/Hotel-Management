package com.hotelManagement.Hotel.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hotelManagement.Hotel.Management.Entities.Booking;
import com.hotelManagement.Hotel.Management.Service.BookingService;
import com.hotelManagement.Hotel.Management.Service.CustomerService;
import com.hotelManagement.Hotel.Management.Service.HotelService;

@RestController
@RequestMapping("/api")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private CustomerService customerService;

	// Only customers can create booking
	@PostMapping("/bookings")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @RequestParam Long hotelId,
			@RequestParam Long custId) {
		Booking savedBooking = bookingService.createBooking(booking, hotelId, custId);
		return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
	}

	// Only admins can get all bookings
	@GetMapping("/bookings/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Booking>> getAllBookings() {
		List<Booking> bookings = bookingService.getBooking();
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	// Admin can get any booking; customer can get only their booking
	@GetMapping("/bookings/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<Booking> getBookingById(@PathVariable Long id, @RequestParam(required = false) Long custId) {
		Booking booking = bookingService.getBookingById(id);

		// Optional: extra check to allow customer only to access their own booking
		if ("ROLE_CUSTOMER".equals(SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator()
				.next().getAuthority()) && !booking.getCustomer().getCustId().equals(custId)) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	// Customer uploads receipt for their booking
	@PostMapping("/bookings/{id}/upload")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<String> uploadReceipt(@PathVariable Long bookingId, @RequestParam MultipartFile file) {
		bookingService.uploadReceipt(bookingId, file);
		return new ResponseEntity<>("Receipt uploaded: " + file.getOriginalFilename(), HttpStatus.CREATED);
	}

	// Customer cancels their booking
	@DeleteMapping("/bookings/{id}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
		bookingService.cancleBooking(id);
		return new ResponseEntity<>("Booking cancelled: " + id, HttpStatus.OK);
	}
}
