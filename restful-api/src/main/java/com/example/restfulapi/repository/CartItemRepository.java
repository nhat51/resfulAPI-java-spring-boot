package com.example.restfulapi.repository;

import com.example.restfulapi.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findCartItemsByCart_Id(int id);
    void deleteCartItemsByCart_Id(int id);
}
