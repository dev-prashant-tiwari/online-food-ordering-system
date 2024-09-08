package com.authentication.authenticationService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.authentication.authenticationService.entity.User;
import com.authentication.authenticationService.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;

	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		return new ResponseEntity(allUsers, HttpStatus.OK);
		
	}
	
	

}
