package com.order.orderService.service;

import com.order.orderService.models.OrderItem;
import com.order.orderService.models.Orders;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    private OrderService  orderService;
@InjectMocks
    private OrderServiceTest orderServiceTest;

    @Test
    public void placeOrderTest(){
        Orders orders = new Orders();
        orders.setUserId(1l);
        orders.setRestaurantId(1l);
        OrderItem item = new OrderItem();
        item.setQuantity(2l);
        item.setMenuitemId(1l);
        List<OrderItem> orderItemList = List.of(item);
        orders.setOrderItemList(orderItemList);
        when(orderService.placeOrder(any(Orders.class))).thenReturn(ResponseEntity.ok(orders));

        // Act
        ResponseEntity<Orders> result = orderService.placeOrder(orders);

        // Assert
        BigDecimal actual = result.getBody().getTotalAmount();
        BigDecimal excpected = new BigDecimal(440);
        Assert.assertEquals(excpected,actual);

    }
}
