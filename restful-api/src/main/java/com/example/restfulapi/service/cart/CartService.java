package com.example.restfulapi.service.cart;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.CartItem;
import com.example.restfulapi.entity.Product;

import java.util.List;

public interface CartService {
        Cart addToCart(String access_token, CartItem cartItem);
        List<CartItem> getAllItem();
        Cart update(String access_token,CartItem cartItem);
        void remove(int productId);
        double calculateTotalPrice();
}
