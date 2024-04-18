package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		
		//HOW TO DENY ALL REQUEST
		
//		http.authorizeHttpRequests()
//		.anyRequest().denyAll()
//		.and().formLogin()
//		.and().httpBasic();
//		return http.build();
		
		
		//CUSTOM ACCESS OF REQUESTS
		
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/myAccount", "/myBalance", "/myCrads").authenticated()
		.requestMatchers("/notices",  "/contact", "/register",
				"/showAllCustomer",
	//			"/login/ale/ale"
				"/login"
//				"/login/{email}/{password}"
				).permitAll()
		.and().formLogin()
		.and().httpBasic();
		return http.build();
		
	}
	
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		//PERMIT ALL REQUESTS
		
//		http.authorizeHttpRequests()
//		.anyRequest().permitAll()
//		.and().formLogin()
//		.and().httpBasic();
//		return http.build();
		
	
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		UserDetails admin=User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("123")
//				.authorities("admin")
//				.build();
//		UserDetails user=User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("123")
//				.authorities("read")
//				.build();
//		return new InMemoryUserDetailsManager(admin,user);
//		
//		UserDetails admin=User.withUsername("admin")
//				.password("123")
//				.authorities("admin")
//				.build();
//		UserDetails user=User.withUsername("user")
//				.password("123")
//				.authorities("read")
//				.build();
//		return new InMemoryUserDetailsManager(admin,user);
//		
//		
//		
//		
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

}
