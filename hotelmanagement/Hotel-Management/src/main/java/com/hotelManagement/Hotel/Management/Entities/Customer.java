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
@Table(name="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long custId;
	
	private String custName;
	
	private String custEmail;
	
	private String custPhone;


	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public Long getCustId() {
		return custId;
	}

	public String getCustName() {
		return custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public String getCustPhone() {
		return custPhone;
	}
}
