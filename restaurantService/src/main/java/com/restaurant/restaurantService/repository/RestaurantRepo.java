package com.restaurant.restaurantService.repository;

import com.restaurant.restaurantService.models.Restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
	@Query("SELECT e FROM Restaurant e where e.email = :email")
  	List<Restaurant> findByUserName(@Param("email") String email);
}
