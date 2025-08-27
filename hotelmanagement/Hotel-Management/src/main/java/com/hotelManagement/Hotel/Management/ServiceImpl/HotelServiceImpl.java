package com.hotelManagement.Hotel.Management.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelManagement.Hotel.Management.Entities.Hotel;
import com.hotelManagement.Hotel.Management.Repository.HotelRepository;
import com.hotelManagement.Hotel.Management.Service.HotelService;
@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	public Hotel createHotel(Hotel h) {
		return hotelRepository.save(h);
	}

	public Hotel getHotel(Long id) {
		return hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
	}

	public List<Hotel> getAllHotels() {
		logger.info("getting all hotels list");
		return hotelRepository.findAll();
	}

	public Hotel updateHotel(Long id, Hotel updatedHotel) {
		Hotel h = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException());
		h.setHotelName(updatedHotel.getHotelName());
		h.setLocation(updatedHotel.getLocation());
		h.setRating(updatedHotel.getRating());
		return hotelRepository.save(h);
	}

}
