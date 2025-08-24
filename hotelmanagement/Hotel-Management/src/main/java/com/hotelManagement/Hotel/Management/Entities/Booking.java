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
