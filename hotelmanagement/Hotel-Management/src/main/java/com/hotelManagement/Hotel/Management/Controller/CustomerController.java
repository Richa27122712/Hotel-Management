package com.hotelManagement.Hotel.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelManagement.Hotel.Management.Entities.Customer;
import com.hotelManagement.Hotel.Management.Service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customers")
	  @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Customer> saveAllCustomer( @RequestBody Customer customer){
		Customer cust=customerService.createCustomer(customer);
		
		return new ResponseEntity<>(cust,HttpStatus.CREATED);
	}
	
	@GetMapping("/customers/all")
	  @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		List<Customer> cust=customerService.getAllCustomer();
		return new ResponseEntity<>(cust,HttpStatus.OK);
	}
	@GetMapping("/customers/{id}")
	  @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
		Customer cust=customerService.getCustomer(id);
		return new ResponseEntity<>(cust,HttpStatus.OK);
	}
	
	@PutMapping("/customers/{id}")
	  @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@RequestBody Customer c){
		Customer cust=customerService.updateCustomer(id,c);
		return new ResponseEntity<>(cust,HttpStatus.CREATED);
	}
	}
	


