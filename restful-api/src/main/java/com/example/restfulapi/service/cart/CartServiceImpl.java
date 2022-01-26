package com.example.restfulapi.service.cart;

import com.example.restfulapi.entity.Cart;
import com.example.restfulapi.entity.CartItem;
import com.example.restfulapi.entity.Product;
import com.example.restfulapi.repository.CartItemRepository;
import com.example.restfulapi.repository.CartRepository;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    @Override
    public ResponseApi addToCart(String access_token, CartItem cartItem) {
        Cart exist = cartRepository.findCartByAccessToken(access_token);
        Product product = productRepository.getById(cartItem.getProductId());
        if (product == null){
            return new ResponseApi(HttpStatus.NOT_FOUND,"product not found","");
        }
        cartItem.setProductName(product.getName());
        cartItem.setThumbnail(product.getThumbnail());
        cartItem.setUnitPrice(product.getPrice());
        //Kiểm tra người dùng có giỏ hàng hay chưa
        if (exist != null) {
            Set<CartItem> listCartItem = exist.getItems();
            for (CartItem item : listCartItem) {
                //kiểm tra sản phẩm có tồn tại trong giỏ hàng hay không
                if (item.getProductId()==cartItem.getProductId()) {
                    item.setQuantity(item.getQuantity() + 1);
                   cartRepository.save(exist);
                }
            }
            cartItem.setCartId(exist.getId());
            listCartItem.add(cartItem);
            exist.setItems(listCartItem);
            return new ResponseApi(HttpStatus.OK,"success",cartRepository.save(exist));
        }
        Cart newCart = new Cart();
        Cart saved = cartRepository.save(newCart);
        newCart.setAccessToken(access_token);
        Set<CartItem> newCartItem = new HashSet<>();
        newCartItem.add(cartItem);
        cartItem.setCartId(saved.getId());
        saved.setItems(newCartItem);
        return cartRepository.save(newCart);
    }

    @Override
    public Cart update(String access_token,CartItem cartItem) {
        Cart exist = cartRepository.findCartByAccessToken(access_token);
        Set<CartItem> cartItemList = exist.getItems();
        for (CartItem item : cartItemList) {
            if (item.getProductId() == cartItem.getProductId()){
                item.setQuantity(cartItem.getQuantity());
                cartItemRepository.save(item);
            }
        }
        return exist;
    }

    @Override
    public Cart remove(String access_token,int productId) {
        Cart exist = cartRepository.findCartByAccessToken(access_token);
        Set<CartItem> itemSet = exist.getItems();
        for (CartItem item: itemSet) {
            if (item.getProductId() == productId){
                itemSet.remove(item);
                cartItemRepository.delete(item);
            }
        }
        return cartRepository.save(exist);
    }

    @Override
    public Cart clear(String access_token) {
        Cart exist = cartRepository.findCartByAccessToken(access_token);
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCart_Id(exist.getId());
        for (CartItem item : cartItemList) {
            cartItemRepository.delete(item);
        }
        return cartRepository.save(exist);
    }

    @Override
    public Cart getCart(String access_token) {
        Cart cart = cartRepository.findCartByAccessToken(access_token);
        return cart;
    }

    @Override
    public double calculateTotalPrice() {
        return 0;
    }
}
