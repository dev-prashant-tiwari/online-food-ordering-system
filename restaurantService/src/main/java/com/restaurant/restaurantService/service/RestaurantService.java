package com.restaurant.restaurantService.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.restaurant.restaurantService.models.*;
import com.restaurant.restaurantService.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.restaurantService.repository.RestaurantRepo;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepo restRepo;

	@Autowired
	private MenuRepository menuRepository;

	public ResponseEntity<Restaurant> getDetails(Long id) {
		try {
			Optional<Restaurant> rest = restRepo.findById(id);
			if(Objects.nonNull(rest) && !rest.isEmpty()) {
				log.info("restaurant found "+ rest.toString());
				return new ResponseEntity(rest, HttpStatus.OK);
			}
		}
		catch(Exception ex) {
			log.warn("Some exception occurred " + ex.getMessage());
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Restaurant>> fetchAllRestaurants() {
		try {
			List<Restaurant> allRestaurants = restRepo.findAll();
			if(Objects.nonNull(allRestaurants) && !allRestaurants.isEmpty()) {
				log.info("restaurants found "+ allRestaurants.toString());
				return new ResponseEntity(allRestaurants, HttpStatus.OK);
			}
		}
		catch(Exception ex) {
			log.warn("Some exception occurred " + ex.getMessage());
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Restaurant> createNewEntry(@Valid Restaurant newRestaurant) {
		Restaurant newEntry = null;
		try{
			newEntry = restRepo.save(newRestaurant);
			log.info("new restaurant added successfully");
		}
		catch(Exception ex) {
			log.info("Some Exception occured "+ ex.getMessage());
			return new ResponseEntity(newEntry, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity(newEntry, HttpStatus.CREATED);
	}

	public ResponseEntity<List<Restaurant>> getRestaurantsByUername(String username) {
		List<Restaurant> myRestaurants = null;
		try {
			myRestaurants = restRepo.findByUserName(username);
			if(Objects.nonNull(myRestaurants) && !myRestaurants.isEmpty()) {
				log.info("restaurants found "+ myRestaurants.toString());
				return new ResponseEntity(myRestaurants, HttpStatus.OK);
			}
			
		}
		catch(Exception ex) {
			return new ResponseEntity(myRestaurants, HttpStatus.OK);
		}
		log.info("No data found with the provided username");
		return new ResponseEntity(myRestaurants, HttpStatus.OK);
		
	}

	public ResponseEntity<Restaurant> updateRestaurant(Restaurant request, Long restaurantId) {
		Restaurant updateEntry = null;
		request.setId(restaurantId);
		try{

			updateEntry = restRepo.save(request);
			log.info("restaurant details updated successfully");
		}
		catch(Exception ex) {
			log.info("Some Exception occured "+ ex.getMessage());
			return new ResponseEntity(updateEntry, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity(updateEntry, HttpStatus.OK);
	}

	public ResponseEntity<String> deleteEntry(Long id) {
		try{
			restRepo.deleteById(id);
			log.info("restaurant deleted form the system successfully");
			return new ResponseEntity("Operation successful", HttpStatus.OK);
		}
		catch(Exception ex) {
			log.info("Some Exception occured "+ ex.getMessage());
			return new ResponseEntity("Some internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<List<Menu>> fetchAllMenu() {

		try {
			List<Menu> allMenu = menuRepository.findAll();
			if(Objects.nonNull(allMenu) && !allMenu.isEmpty()) {
				log.info("restaurants found "+ allMenu.toString());
				return new ResponseEntity(allMenu, HttpStatus.OK);
			}
		}
		catch(Exception ex) {
			log.warn("Some exception occurred " + ex.getMessage());
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Menu>> fetchIndividuaMenu(Long restaurantId) {

		try {
			List<Menu> menu = restRepo.findById(restaurantId).get().getMenuList();
			if(Objects.nonNull(menu) && !menu.isEmpty()) {
				log.info("restaurants found "+ menu.toString());
				return new ResponseEntity(menu, HttpStatus.OK);
			}
		}
		catch(Exception ex) {
			log.warn("Some exception occurred " + ex.getMessage());
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}
}
