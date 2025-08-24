package com.hotelManagement.Hotel.Management.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelManagement.Hotel.Management.Entities.Customer;
import com.hotelManagement.Hotel.Management.Repository.CustomerRepository;
import com.hotelManagement.Hotel.Management.Service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository cutomerRepository;

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


	public Customer createCustomer(Customer c) {
		return cutomerRepository.save(c);
	}
	
	
	
	
	public Customer getCustomer(Long id) {
		return cutomerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
	}
	
	
	public List<Customer> getAllCustomer() {
		return cutomerRepository.findAll();
	}
	
	public Customer updateCustomer(Long id,Customer updatedCustomer) {
		Customer c=cutomerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));
		
		c.setCustEmail(updatedCustomer.getCustEmail());
		 c.setCustName(updatedCustomer.getCustName());
		   
		    c.setCustPhone(updatedCustomer.getCustPhone());

		    return cutomerRepository.save(c);

}
}