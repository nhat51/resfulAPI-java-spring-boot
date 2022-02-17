package com.example.restfulapi.service.cart;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.CartItem;
import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entity.Product;
import com.example.restfulapi.response.ResponseApi;

import java.util.List;

public interface CartService {
        ResponseApi addToCart(String access_token, CartItem cartItem);
        ResponseApi update(String access_token,CartItem cartItem);
        ResponseApi remove(String access_token,int productId);
        ResponseApi clear(String access_token);
        ResponseApi getCart(String access_token);
        Order prepareOrder(String access_token,Order order);
        double calculateTotalPrice();
}
