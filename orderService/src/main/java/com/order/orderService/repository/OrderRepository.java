package com.order.orderService.repository;

import com.order.orderService.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Query("Select e from Orders e where e.orderStatus = :orderStatus")
    public List<Orders> findByOrderStatus(@Param("orderStatus") String status);
}
