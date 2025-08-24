package com.hotelManagement.Hotel.Management.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotelManagement.Hotel.Management.Entities.Booking;

@Service
public interface BookingService {
	
	Booking createBooking(Booking booking,Long hotelId,Long custId);
	
	 List< Booking >getBooking();
	 
	 Booking getBookingById(Long bookingId);
	 
	 void uploadReceipt(Long bookingId,MultipartFile file);
	 
	 void cancleBooking(Long bookinId);
	 
	 

}
