package com.authentication.authenticationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.authenticationService.models.JwtRequest;
import com.authentication.authenticationService.models.JwtResponse;
import com.authentication.authenticationService.security.JwtHelper;
import com.authentication.authenticationService.service.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		this.doAuthenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtHelper.generateToken(userDetails);
		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
		
		try{
			authenticationManager.authenticate(authentication);
		}
		catch(BadCredentialsException e){
			throw new RuntimeException("Invalid Username or Password");
		}
		
	}
	
	
	
	@ExceptionHandler
	public String handleException(Exception ex) {
		return ex.getMessage();
	}

}
