package com.restaurant.restaurantService.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuItemRequest {
    private  Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
