package com.example.restfulapi.service.Cart;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.CartItem;
import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entity.Product;
import com.example.restfulapi.repository.CartItemRepository;
import com.example.restfulapi.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    private HashMap<Integer, CartItem> mapItem;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public void addToCart(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            return;
        }
        if (mapItem == null) {
            mapItem = new HashMap<>();
        }
        CartItem item = null;
        if (mapItem.containsKey(product.getId())) {
            item = mapItem.get(product.getId());
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item = new CartItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setThumbnail(product.getThumbnail());
            item.setUnitPrice(product.getPrice());
            item.setQuantity(quantity);
        }
        mapItem.put(product.getId(), item);
        Set<CartItem> cartItems = changeMapToSet(mapItem);
        for (CartItem cartItem: cartItems) {
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void updateCart(Product product, int quantity) {

    }

    @Override
    public void remove(int productId) {

    }

    @Override
    public Order prepareOrder() {
        return null;
    }

    public Set<CartItem> changeMapToSet(HashMap<Integer, CartItem> mapItem) {
        Set<CartItem> setItem = new HashSet<CartItem>(mapItem.values());
        return setItem;
    }
}
