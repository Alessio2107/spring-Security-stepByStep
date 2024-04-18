package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {

	@GetMapping("/adminPage")
	public String getAccountDetails() {
		return "Welcome admin";
	}
}
