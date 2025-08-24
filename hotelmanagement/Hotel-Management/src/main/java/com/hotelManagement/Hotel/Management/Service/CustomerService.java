package com.hotelManagement.Hotel.Management.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelManagement.Hotel.Management.Entities.Customer;

@Service
public interface CustomerService {
	
	Customer createCustomer(Customer c) ;
	
	Customer getCustomer(Long id) ;
	
	List<Customer> getAllCustomer();
	
	Customer updateCustomer(Long id,Customer updatedCustomer);

}
