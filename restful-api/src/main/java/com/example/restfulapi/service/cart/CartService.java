package com.example.restfulapi.service.cart;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.CartItem;
import com.example.restfulapi.entity.Product;

import java.util.List;

public interface CartService {
        Cart addToCart(String access_token, CartItem cartItem);
        Cart update(String access_token,CartItem cartItem);
        Cart remove(String access_token,int productId);
        Cart clear(String access_token);
        Cart getCart(String access_token);
        double calculateTotalPrice();
}
