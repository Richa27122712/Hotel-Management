package com.hotelManagement.Hotel.Management.Controller;


import com.hotelManagement.Hotel.Management.Entities.Customer;
import com.hotelManagement.Hotel.Management.Repository.CustomerRepository;
import com.hotelManagement.Hotel.Management.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register new user
    @PostMapping("/register")
    public String register(@RequestBody Customer customer) {
        if (customerRepository.findByCustEmail(customer.getCustEmail()) != null) {
            return "User already exists!";
        }
        customerRepository.save(customer);
        return "User registered successfully!";
    }

    // ✅ Login and return JWT token
    @PostMapping("/login")
    public String login(@RequestBody Customer customer) {
        Customer dbCustomer = customerRepository.findByCustEmail(customer.getCustEmail());
        if (dbCustomer != null && dbCustomer.getPassword().equals(customer.getPassword())) {
            // generate JWT token with email + role
            String token = jwtUtil.generateToken(dbCustomer.getCustEmail(), dbCustomer.getRole());
            return token;
        }
        return "Invalid credentials!";
    }
}
