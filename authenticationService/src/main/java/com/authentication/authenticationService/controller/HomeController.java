package com.authentication.authenticationService.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.authenticationService.entity.User;
import com.authentication.authenticationService.repository.UserRepository;
import com.authentication.authenticationService.service.UserService;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService service;
	private UserRepository userRepo;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getUser() {
		return service.getAllUsers();
	}
	
}
