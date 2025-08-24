package com.hotelManagement.Hotel.Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelManagement.Hotel.Management.Entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
