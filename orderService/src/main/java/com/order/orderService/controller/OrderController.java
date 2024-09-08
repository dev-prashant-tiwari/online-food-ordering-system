package com.order.orderService.controller;

import com.order.orderService.constants.OrderConstants;
import com.order.orderService.models.Orders;
import com.order.orderService.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping(value = "/placeOrder")
    public ResponseEntity<Orders> placeOrder(@RequestBody Orders order){
        log.info("logger for debugging purpose");
        return orderService.placeOrder(order);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Orders>> getAll(){
        return  orderService.getAllOrders();
    }

    @GetMapping("/myOrder/{id}")
    public ResponseEntity<Orders> getMyOrder(@PathVariable Long id){
        return orderService.fetchMyOrder(id);
    }

    /**
     * This restarant cook or delivery boy can update the order status
     * @return
     */
    @PutMapping("/prepared/{orderid}")
    public String updateStausByCook (@PathVariable Long orderid){
        return orderService.updateOrderStatus(orderid,String.valueOf(OrderConstants.OrderStatus.Ready));
    }

    @PutMapping("/aceept-order/{orderid}")
    public String updateStausByRestaurant(@PathVariable Long orderid){
        return orderService.updateOrderStatus(orderid,String.valueOf(OrderConstants.OrderStatus.Accepted));
    }

    @PutMapping("/delivered/{orderid}")
    public String updateStatusByDeliveryPerson (@PathVariable("orderid") Long id){
        return orderService.updateOrderStatus(id,String.valueOf(OrderConstants.OrderStatus.Delivered));
    }

    @PutMapping("/paymentDone/{orderid}")
    public String updatePayment (@PathVariable("orderid") Long id){
        return orderService.updatePayment(id,String.valueOf(OrderConstants.PaymentStatus.Paid));
    }

    /**
     * This will provide current status of the order
     * @return
     */
    @GetMapping("/get-order-status/{orderid}")
    public String getOrderStatus(@PathVariable("orderid") Long id){
        return orderService.getOrderStatus(id);
    }

    /**
     * This will provide the order information which are ready to deliver
     * @return
     */
    @GetMapping("/get-ready-orders")
    public ResponseEntity<List<Orders>> getReadyOrders(){
        return orderService.fetchReadyOrders();
    }


    @ExceptionHandler
    public  String handlingExceptions(Exception ex){
        return "some exception occurred "+ex.getMessage();
    }

}
