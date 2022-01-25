package com.example.restfulapi.service.cart;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.CartItem;
import com.example.restfulapi.entity.Product;
import com.example.restfulapi.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;


    @Override
    public void createCart(Cart cart) {

    }

    @Override
    public Cart addToCart(int customerId, CartItem cartItem) {
        Cart exist = cartRepository.findCartByCustomerId(customerId);
        if (exist != null) {
            Set<CartItem> listCartItem = exist.getItems();
            //kiem tra san pham co to tai hay k
            for (CartItem item : listCartItem) {
                if (item.getProductId() == cartItem.getProductId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    break;
                }
            }
            listCartItem.add(cartItem);
            exist.setItems(listCartItem);
            return cartRepository.save(exist);
        }
        Cart newCart = new Cart();
        Cart saved = cartRepository.save(newCart);
        newCart.setCustomerId(customerId);
        Set<CartItem> newCartItem = new HashSet<>();
        newCartItem.add(cartItem);
        cartItem.setCartId(saved.getId());
        saved.setItems(newCartItem);
        return cartRepository.save(newCart);
    }


    @Override
    public List<CartItem> getAllItem() {
        return null;
    }

    @Override
    public void update(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            return;
        }

    }

    @Override
    public void remove(int productId) {

    }

    @Override
    public double calculateTotalPrice() {
        return 0;
    }
}
