package com.example.restfulapi.service.cart;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.CartItem;
import com.example.restfulapi.entity.Product;

import java.util.List;

public interface CartService {
        void createCart(Cart cart);
        Cart addToCart(int customerId, CartItem cartItem);
        List<CartItem> getAllItem();
        void update(Product product,int quantity);
        void remove(int productId);
        double calculateTotalPrice();
}
