package com.hotelManagement.Hotel.Management.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;
	
	private String hotelName;
	
	private String location;
	
	private int rating;
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public String getLocation() {
		return location;
	}

	public int getRating() {
		return rating;
	}

	
	
	

}
