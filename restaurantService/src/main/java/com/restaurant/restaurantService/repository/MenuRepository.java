package com.restaurant.restaurantService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurantService.models.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
