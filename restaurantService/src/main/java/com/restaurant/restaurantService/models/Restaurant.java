package com.restaurant.restaurantService.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Table(name = "restaurants")
@Data
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank (message = "Restaurant name can't be blank")
	private String name;

	@NotNull
	@NotBlank (message = "Restaurant email can't be blank")
	private String email;
	
	@NotNull
	@NotBlank (message = "Restaurant name can't be blank")
	private String ownerName;
	
	private String description;
	
	@NotNull
	@NotBlank (message = "Restaurant name can't be blank")
	private String address;

	private String phone;
	
	private Boolean isOpen = true;
	
	@OneToMany(targetEntity = Menu.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private List<Menu> menuList;
	

}
