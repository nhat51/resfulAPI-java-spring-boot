/*
package com.example.restfulapi.controller;

import com.example.restfulapi.entity.CartItem;
import com.example.restfulapi.entity.Product;
import com.example.restfulapi.entity.ShoppingCart;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

*/
/*@RestController
@RequestMapping("api/v1/carts")*//*

public class CartController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

   */
/* @RequestMapping(method = RequestMethod.POST, path = "/add")
    public CartItem addToCart(@RequestParam(name = "productId") int productId,
                              @RequestParam(name = "shoppingCartId") int shoppingCartId

    ){
        ShoppingCart shoppingCart = new ShoppingCart();
        if (shoppingCartId != 0) {
            shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        }
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) throw new RuntimeException("san pham khong ton tai");
        CartItem cart = new CartItem();
        cart.setProductId(productId);
        cart.setQuantity(1);
        cart.setUnitPrice(product.getPrice());

    }*//*

}
*/
