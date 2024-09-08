package com.restaurant.restaurantService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class RestaurantRequest {

    private String name;



    private String ownerName;

    private String description;


    private String address;

    private String phone;

    private Boolean isOpen = true;

    private List<MenuRequest> menuList;
}
