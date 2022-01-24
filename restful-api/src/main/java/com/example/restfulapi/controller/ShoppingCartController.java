package com.example.restfulapi.controller;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.Product;
import com.example.restfulapi.entity.ShoppingCart;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.service.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


public class ShoppingCartController {

    public static HashMap<Integer, ShoppingCart> listShoppingCart; // bựa
    // xịn thì móc trong db ra.

    @Autowired
    CartService service;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET,path = "list")
    public List<Cart> findAll(){
       return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "add")
    public boolean addToCart(@RequestHeader String accessToken, @RequestParam int productId, @RequestParam int quantity){
        int accountId = 1; // lấy từ accessToken.
        if(listShoppingCart == null){
            listShoppingCart = new HashMap<>();
        }
        ShoppingCart currentShoppintCart = null; // cho người dùng hiện tại.
        if(listShoppingCart.containsKey(accountId)){
            currentShoppintCart = listShoppingCart.get(accountId);
        }else{
            currentShoppintCart = new ShoppingCart();
        }

        Product product = productRepository.getById(productId);
        if(product == null){
            return false;
        }
        currentShoppintCart.add(product, quantity);
        // save vào biến static.
        listShoppingCart.put(accountId, currentShoppintCart);
        return true;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ShoppingCart getCart(@RequestHeader String accessToken){
        int accountId = 1; // lấy từ accessToken.
        ShoppingCart currentShoppintCart = null; // cho người dùng hiện tại.
        if(listShoppingCart.containsKey(accountId)){
            currentShoppintCart = listShoppingCart.get(accountId);
        }else{
            currentShoppintCart = new ShoppingCart();
        }
        return currentShoppintCart;
    }

}
