package com.order.orderService.service;

import com.order.orderService.constants.OrderConstants;
import com.order.orderService.models.MenuItem;
import com.order.orderService.models.Orders;
import com.order.orderService.repository.MenuItemRepository;
import com.order.orderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class OrderService {





    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;
    public ResponseEntity<Orders> placeOrder(Orders orders) {

        try{
            AtomicReference<BigDecimal> totalPayment = new AtomicReference<>(BigDecimal.ZERO);
            orders.getOrderItemList().forEach(item -> {
                Optional<MenuItem> menuItem = menuItemRepository.findById(item.getMenuitemId());
                BigDecimal quantity = new BigDecimal(item.getQuantity());
                BigDecimal price = quantity.multiply(menuItem.get().getPrice());
                item.setPrice(price);
                totalPayment.updateAndGet(currentTotal -> currentTotal.add(price));

            });
            orders.setTotalAmount(totalPayment.get());
            orders.setOrderStatus(String.valueOf(OrderConstants.OrderStatus.Placed));
            orders.setPaymentStatus(String.valueOf(OrderConstants.PaymentStatus.Pending));
            Orders placedOrder = orderRepository.save(orders);
            return new ResponseEntity(placedOrder, HttpStatus.OK);
        }
        catch (Exception ex){
            log.warn("Some exception occurred "+ex.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Orders>> getAllOrders() {
        try{
            List<Orders> placedOrders = orderRepository.findAll();
            return new ResponseEntity(placedOrders, HttpStatus.OK);
        }
        catch (Exception ex){
            log.warn("Some exception occurred "+ex.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Orders>> fetchReadyOrders() {

        try{
            List<Orders> placedOrders = orderRepository.findByOrderStatus(String.valueOf(OrderConstants.OrderStatus.Ready));
            return new ResponseEntity(placedOrders, HttpStatus.OK);
        }
        catch (Exception ex){
            log.warn("Some exception occurred "+ex.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Orders> fetchMyOrder(Long id) {
        try{
            Optional<Orders> myOrder = orderRepository.findById(id);
            return new ResponseEntity(myOrder, HttpStatus.OK);
        }
        catch (Exception ex){
            log.warn("Some exception occurred "+ex.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getOrderStatus(Long id) {
        Optional<Orders> order = orderRepository.findById(id);
        if(Objects.nonNull(order)){
            return order.get().getOrderStatus();
        }
        return "Invalid order id";
    }

    public String updateOrderStatus(Long id, String status) {
        Optional<Orders> order = orderRepository.findById(id);
        if(Objects.nonNull(order)){
            order.get().setOrderStatus(status);
            orderRepository.save(order.get());
            return "Status updated successfully";
        }
        return "Invalid  order id";
    }

    public String updatePayment(Long id, String paymentStatus) {
        Optional<Orders> order = orderRepository.findById(id);
        if(Objects.nonNull(order)){
            order.get().setPaymentStatus(paymentStatus);
            orderRepository.save(order.get());
            return "Status updated successfully";
        }
        return "Invalid  order id";
    }
}
