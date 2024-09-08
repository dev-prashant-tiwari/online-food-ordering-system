package com.userService.UserService.controller;

import java.util.List;

import com.userService.UserService.entity.User;
import com.userService.UserService.models.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.userService.UserService.service.CustomerService;

import jakarta.validation.Valid;


/**
 * This is UserController Class
 */
@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private CustomerService service;

	/**
	 * To get the individual customer
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getCustomer(@PathVariable Long id) {
		
		return service.fetchUserDetails(id);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody User user){

		return service.createUser(user);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUserProfile(@RequestBody UpdateRequest request, @PathVariable Long id){
		return service.updateUser(request,id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteUser(@PathVariable Long id){
		return service.deleteCustomer(id);
		
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUsers(){
		return service.getAllCustomers();
	}

}
