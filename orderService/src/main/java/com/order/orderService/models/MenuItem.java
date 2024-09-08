package com.order.orderService.models;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "menuitem")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String menu_id;
    private String description;
    private BigDecimal price;

//    @OneToOne(targetEntity = OrderItem.class, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "menu_item_id", referencedColumnName = "id")
//    private OrderItem item;



}

