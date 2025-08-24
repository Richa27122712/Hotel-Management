package com.hotelManagement.Hotel.Management.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelManagement.Hotel.Management.Entities.Hotel;

@Service
public interface HotelService {
	
	Hotel createHotel(Hotel h);
	
	Hotel getHotel(Long id);
	
	List<Hotel> getAllHotels() ;
	
	Hotel updateHotel(Long id, Hotel updatedHotel);

}
