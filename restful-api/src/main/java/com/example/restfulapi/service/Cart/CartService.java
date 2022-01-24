package com.example.restfulapi.service.Cart;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entity.Product;

import java.util.List;

public interface CartService {
    List<Cart> findAll();
    void addToCart(Product product, int quantity);
    void updateCart(Product product, int quantity);
    void remove(int productId);
    Order prepareOrder();
}
