package com.order.orderService.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long restaurantId;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String paymentStatus;
    @OneToMany(targetEntity = OrderItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderItem> orderItemList;
}
