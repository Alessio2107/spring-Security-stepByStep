package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.demo.controller", "com.example.demo.security"})
public class FirstSpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringSecurityAppApplication.class, args);
		System.out.println("ciao");
	}

}
