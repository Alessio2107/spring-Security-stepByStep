package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
public class LoginController {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		Customer savedCustomer=null;
		ResponseEntity response=null;
		try {
			
			String hashPwd=passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			
			
			savedCustomer=customerRepository.save(customer);
			if(savedCustomer.getId()>0) {
				response=ResponseEntity
						.status(HttpStatus.CREATED)
						.body("Given user details are successfully registered");
			}
			
		}catch (Exception ex) {
			// TODO: handle exception
			response= ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to "+ex.getMessage());
		}
		return response;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
		
//	@PostMapping("/login/{email}/{password}")
//	public ResponseEntity<String> loginUser(@PathVariable String email, @PathVariable String password) {
		
	    List<Customer> customers = customerRepository.findByEmail(email);
	    
	    boolean loginSuccess = customers.stream()
	        .anyMatch(customer -> passwordEncoder.matches(password, customer.getPwd()));

	    if (loginSuccess) {
	    	String userRole =
	    			customers.get(0).getRole();
	        return ResponseEntity.ok("Login successful! Role: " + userRole);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}
	
	@GetMapping("/showAllCustomer")
	public Iterable<Customer> showAll(){
		return customerRepository.findAll();		
	}

}
