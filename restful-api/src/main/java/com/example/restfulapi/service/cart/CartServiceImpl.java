package com.example.restfulapi.service.cart;

import com.example.restfulapi.entity.*;
import com.example.restfulapi.entityDTO.OrderDto;
import com.example.restfulapi.repository.CartItemRepository;
import com.example.restfulapi.repository.CartRepository;
import com.example.restfulapi.repository.OrderRepository;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.service.order.OrderService;
import com.example.restfulapi.status.OrderStatus;
import com.example.restfulapi.status.Status;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

import static com.example.restfulapi.queue.Config.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;
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
                /*
                kiểm tra sản phẩm có tồn tại trong giỏ hàng hay không
                * nếu có thì tăng quantity và lưu lại
                *
                * */
                if (item.getProductId()==cartItem.getProductId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    exist.setTotalMoney();
                    return new ResponseApi(HttpStatus.OK,"success",cartRepository.save(exist));
                }
            }
            cartItem.setCartId(exist.getId());
            listCartItem.add(cartItem);
            exist.setItems(listCartItem);
            exist.setTotalMoney();
            return new ResponseApi(HttpStatus.OK,"success",cartRepository.save(exist));
        }
        Cart newCart = new Cart();
        Cart saved = cartRepository.save(newCart);
        newCart.setAccessToken(access_token);
        Set<CartItem> newCartItem = new HashSet<>();
        newCartItem.add(cartItem);
        cartItem.setCartId(saved.getId());
        saved.setItems(newCartItem);
        saved.setTotalMoney();
        return new ResponseApi(HttpStatus.OK,"success",cartRepository.save(newCart));
    }

    @Override
    public ResponseApi update(String access_token,CartItem cartItem) {
        Cart exist = cartRepository.findCartByAccessToken(access_token);
        Set<CartItem> cartItemList = exist.getItems();
        for (CartItem item : cartItemList) {
            if (item.getProductId() == cartItem.getProductId()){
                item.setQuantity(cartItem.getQuantity());
                cartItemRepository.save(item);
            }
        }
        return new ResponseApi(HttpStatus.OK,"updated",exist);
    }

    @Override
    public ResponseApi remove(String access_token,int productId) {
        Cart exist = cartRepository.findCartByAccessToken(access_token);
        Set<CartItem> itemSet = exist.getItems();
        for (CartItem item: itemSet) {
            if (item.getProductId() == productId){
                itemSet.remove(item);
                cartItemRepository.delete(item);
            }
        }
        exist.setTotalMoney();
        return new ResponseApi(HttpStatus.OK,"success",exist);
    }

    @Override
    public ResponseApi clear(String access_token) {
        //tìm cái cart theo access token
        Cart exist = cartRepository.findCartByAccessToken(access_token);
        Set<CartItem> itemSet = exist.getItems();
        itemSet.clear();
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCart_Id(exist.getId());
        cartItemRepository.deleteAll(cartItemList);
        exist.setTotalMoney();
        return new ResponseApi(HttpStatus.OK,"success",exist);
    }

    @Override
    public ResponseApi getCart(String access_token) {
        Cart cart = cartRepository.findCartByAccessToken(access_token);
        cart.setTotalMoney();
        return new ResponseApi(HttpStatus.OK,"success",cart);
    }

    /*
    * Chuyển từ cart sang order
    * */
    @Override
    public Order prepareOrder(String access_token) {
        //tìm giỏ hàng theo access token
        Cart cart = cartRepository.findCartByAccessToken(access_token);
        Order order = new Order(); // generate id, tính tổng tiền, set ngày tháng, set các thông tin
        Set<OrderDetail> orderDetails = new HashSet<>();
        // chuyển từ cart item sang order detail
        for (CartItem cartItem : cart.getItems()) {
            Product product = productRepository.getById(cartItem.getProductId());
            OrderDetailId key = new OrderDetailId();

            key.setProductId(cartItem.getProductId());
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetail.setId(key);

            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setUnitPrice(cartItem.getUnitPrice());
            orderDetail.setProductId(cartItem.getProductId());
            orderDetails.add(orderDetail);
        }
        order.setCustomerId(1);
        order.setStatus(OrderStatus.PENDING.name());
        order.setPayment_status(Status.PaymentStatus.PENDING.name());
        order.setCreated_at(LocalDate.now());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        clear(access_token);
        cart.setTotalMoney();
        cartRepository.save(cart);
        try{
            OrderDto orderDto = new OrderDto(order);
            rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,DIRECT_ROUTING_KEY_ORDER,orderDto);
        }catch (Exception e){

        }
        return order;
    }

    @Override
    public double calculateTotalPrice() {
        return 0;
    }
}
