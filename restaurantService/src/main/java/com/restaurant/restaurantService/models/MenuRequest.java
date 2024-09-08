package com.restaurant.restaurantService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class MenuRequest {

    private Long id;
    private String name;

    private String description;

    private List<MenuItemRequest> item;
}
