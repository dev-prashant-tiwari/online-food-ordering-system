package com.restaurant.restaurantService.controller;

import java.util.List;

import com.restaurant.restaurantService.models.Menu;
import com.restaurant.restaurantService.models.MenuRequest;
import com.restaurant.restaurantService.models.RestaurantRequest;
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

import com.restaurant.restaurantService.models.Restaurant;
import com.restaurant.restaurantService.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService service;
	
	
	@GetMapping("/getDetails/{id}")
	public ResponseEntity<Restaurant> getRestaurantDetails (@PathVariable Long id){

		return service.getDetails(id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Restaurant>> getAllRestaurants(){

		return service.fetchAllRestaurants();
	}
	
	@GetMapping("/getMyRestaurant/{username}")
	public ResponseEntity<List<Restaurant>> getAllRestaurantsByOwnerName(@PathVariable String username){
		return service.getRestaurantsByUername(username);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant newRestaurant){
		return service.createNewEntry(newRestaurant);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Restaurant> updateRestaurantDetail(@Valid @RequestBody Restaurant request, @PathVariable Long id){
		return service.updateRestaurant(request,id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> addRestaurant(@PathVariable Long id){
		return service.deleteEntry(id);
	}

	/**
	 * This function will provide the all menus of all restaurants
	 * @return
	 */
	@GetMapping("/getAllMenu")
	public ResponseEntity<List<Menu>> getAllMenu(){
		return service.fetchAllMenu();
	}

	/**
	 * this function will provide menu of individual restaurants
	 * @return
	 */
	@GetMapping("/getMenu/{restaurantId}")
	public ResponseEntity<List<Menu>> getIndividualMenu(@PathVariable Long restaurantId){
		return service.fetchIndividuaMenu(restaurantId);
	}

}
