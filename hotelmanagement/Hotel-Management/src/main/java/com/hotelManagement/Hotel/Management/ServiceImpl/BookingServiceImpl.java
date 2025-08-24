package com.hotelManagement.Hotel.Management.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotelManagement.Hotel.Management.Entities.Booking;
import com.hotelManagement.Hotel.Management.Entities.Customer;
import com.hotelManagement.Hotel.Management.Entities.Hotel;
import com.hotelManagement.Hotel.Management.Repository.BookingRepository;
import com.hotelManagement.Hotel.Management.Repository.CustomerRepository;
import com.hotelManagement.Hotel.Management.Repository.HotelRepository;
import com.hotelManagement.Hotel.Management.Service.BookingService;
@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private CustomerRepository cutomerRepository;




	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	public Booking createBooking(Booking booking,Long hotelId,Long custId) {
		Customer customer=cutomerRepository.findById(custId).orElseThrow(()->new RuntimeException("customer not find"));
		Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new RuntimeException("hotel not find"));
		
		booking.setCustomer(customer);
		booking.setHotel(hotel);
		
	return bookingRepository.save(booking);
		
	}
	
	public List< Booking >getBooking() {
		
	return	bookingRepository.findAll();
	}
	
public Booking getBookingById(Long bookingId) {
	return bookingRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("Booking not found"));
}

public void uploadReceipt(Long bookingId,MultipartFile file) {
	Booking booking=bookingRepository.findById(bookingId).orElseThrow(()->new RuntimeException("Booking not find7"));
	booking.setFilepath(file.getOriginalFilename());
	bookingRepository.save(booking);
	
}
public void cancleBooking(Long bookingId) {
	bookingRepository.deleteById(bookingId);
}

}
