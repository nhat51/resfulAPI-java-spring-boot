package com.example.restfulapi.repository;

import com.example.restfulapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findOrdersByCustomerName(String customer);
}
