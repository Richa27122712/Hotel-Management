package com.hotelManagement.Hotel.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
	
	
	@PostMapping("/bookings")
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking,@RequestParam Long hotelId,@RequestParam Long custId){
		Booking bookings=bookingService.createBooking(booking, hotelId, custId);
		return new ResponseEntity<>(bookings,HttpStatus.CREATED);

}
	@GetMapping("/bookings/all")
	public ResponseEntity<List<Booking>> getAllBooking(){
		List<Booking> booking=bookingService.getBooking();
		return new ResponseEntity<>(booking,HttpStatus.OK);
		
		
	}
	@GetMapping("/bookings/{id}")
	public ResponseEntity<Booking> getBookingById(Long id){
		Booking booking=bookingService.getBookingById(id);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	@PostMapping("/bookings/upload")
	public ResponseEntity<String> uploadReceipt(@PathVariable Long bookingId,@RequestParam MultipartFile file){
		bookingService.uploadReceipt(bookingId, file);
		return new ResponseEntity<>("receipt uploaded" + file.getOriginalFilename(),HttpStatus.CREATED);
	}
	@DeleteMapping("/bookings/{id}")
	
	public ResponseEntity<String> cancleBooking(@PathVariable Long bookingId){
	bookingService.cancleBooking(bookingId);
		return new ResponseEntity<>("Booking is cancled" + bookingId,HttpStatus.OK);
		
	}	 
		
		 
}