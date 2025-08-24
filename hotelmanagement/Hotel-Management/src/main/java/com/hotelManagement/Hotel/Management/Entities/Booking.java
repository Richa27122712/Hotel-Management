package com.hotelManagement.Hotel.Management.Entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="booking")
@Data
@NoArgsConstructor
@AllArgsConstructor



public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	private String bookingNumber;
	
	private LocalDate checkInDate;

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public String getStatus() {
		return status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public String getFilepath() {
		return filepath;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Hotel getHotel() {
		return hotel;
	}

	private LocalDate checkOutDate;
	
	private double totalAmount;
	
	private String status;
	
	private String filepath;
	
	
	@ManyToOne
	@JoinColumn(name="custID")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="hotelId")
	private Hotel hotel;

}
