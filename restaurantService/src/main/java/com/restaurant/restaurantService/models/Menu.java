package com.restaurant.restaurantService.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank(message = "Item name can't be blank")
	private String name;
	
	private String description;

	private  Long restaurant_id;

	@OneToMany(targetEntity = MenuItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name ="menu_id", referencedColumnName = "id")
	private List<MenuItem> item;


}
