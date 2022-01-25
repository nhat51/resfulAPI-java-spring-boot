package com.example.restfulapi.repository;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByCustomerId(int id);
}
