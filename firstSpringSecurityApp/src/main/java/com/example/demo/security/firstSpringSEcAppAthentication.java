package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
@Component
public class firstSpringSEcAppAthentication implements AuthenticationProvider{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username=authentication.getName();
		String pwd=authentication.getCredentials().toString();
		List<Customer> customers=customerRepository.findByEmail(username);
		if(customers.size()>0) {
			
			if(passwordEncoder.matches(pwd, customers.get(0).getPwd())) {
				List<GrantedAuthority> authorities=new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
				
				return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
				
				}else {
					
				throw new BadCredentialsException("Invalid password");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

