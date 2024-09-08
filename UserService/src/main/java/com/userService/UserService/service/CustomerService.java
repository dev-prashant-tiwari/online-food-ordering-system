package com.userService.UserService.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.userService.UserService.entity.User;
import com.userService.UserService.models.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userService.UserService.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CustomerService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<User> fetchUserDetails(Long id) {
		try {
			Optional<User> user = repository.findById(id);
			if(Objects.nonNull(user))
			return new ResponseEntity(user, HttpStatus.OK);
		}
		catch(Exception ex) {
			log.warn("some exception occurred" + ex.getMessage());
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		
	}

	/**
	 * This function is being used for registering the new user
	 * @param user
	 * @return
	 */
	public ResponseEntity<?> createUser(User user) {
		try{
			String plainpassword = user.getPassword();
			user.setPassword(passwordEncoder.encode(plainpassword));
			User createdUser = repository.save(user);
			return new ResponseEntity(createdUser, HttpStatus.CREATED);
		}
		catch(Exception ex) {
			log.warn("Some exception occurred" + ex.getMessage());

			return new ResponseEntity(ex.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}

	public ResponseEntity<User> updateUser(UpdateRequest request,Long userId) {
		
		try{
			Optional<User> user = repository.findById(userId);
			if(Objects.nonNull(user)){
				user.get().setFirstName(request.getFirstName());
				user.get().setLastName(request.getLastName());
				user.get().setPhone(request.getPhone());
				user.get().setAddress(request.getAdress());
				User updatedUser = repository.save(user.get());
				return new ResponseEntity(updatedUser, HttpStatus.OK);
			}

		}
		catch(Exception ex) {
			log.warn("Some exception occurred" + ex.getMessage());
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	public ResponseEntity<String> deleteCustomer(Long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity("user deleted successfully ", HttpStatus.OK);
		}
		catch(Exception ex) {
			log.warn("Some exception occurred" + ex.getMessage());
			return new ResponseEntity("some exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

    public ResponseEntity<List<User>> getAllCustomers() {
		try {
			List<User> users = repository.findAll();
			if(Objects.nonNull(users))
				return new ResponseEntity(users, HttpStatus.OK);
		}
		catch(Exception ex) {
			log.warn("some exception occurred" + ex.getMessage());
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
