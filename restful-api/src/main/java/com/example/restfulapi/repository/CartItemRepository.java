package com.example.restfulapi.repository;

import com.example.restfulapi.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    CartItem findCartItemByProductId(int id);
}
