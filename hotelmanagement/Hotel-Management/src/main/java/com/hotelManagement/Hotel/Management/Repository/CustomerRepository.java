package com.hotelManagement.Hotel.Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelManagement.Hotel.Management.Entities.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByCustEmail(String email);

}
