package com.example.restfulapi.service.order;

import com.example.restfulapi.entity.*;
import com.example.restfulapi.repository.*;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.service.product.ProductService;
import com.example.restfulapi.status.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public ResponseApi listOrder() {
        return new ResponseApi(HttpStatus.OK,"success",orderRepository.findAll());
    }

    @Override
    public Order createOrder(String access_token,Order order) {
        Order newOrder = new Order();
        newOrder.setShipName(order.getShipName());
        newOrder.setCreated_at(LocalDate.now());
        newOrder.setUpdated_at(order.getUpdated_at());
        newOrder.setShipAddress(order.getShipAddress());
        newOrder.setCustomerId(order.getCustomerId());
        newOrder.setStatus(OrderStatus.PENDING.name());
        //tìm cart theo access token
        Cart cart = cartRepository.findCartByAccessToken(access_token);
        Set<OrderDetail> listOrderDetail = new HashSet<>();
        /*for (OrderDetail od: order.getOrderDetails()) {
            Product product = productRepository.findById(od.getId().getProductId()).get();
            System.out.println(product.getId());
            OrderDetailId key = new OrderDetailId();

            key.setProductId(od.getId().getProductId());

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrder(newOrder);
            orderDetail.setProduct(product);

            orderDetail.setId(key);

            orderDetail.setQuantity(od.getQuantity());
            orderDetail.setUnitPrice(od.getUnitPrice());
            orderDetail.setOrder(newOrder);
            listOrderDetail.add(orderDetail);
        }*/

        newOrder.setOrderDetails(listOrderDetail);
        orderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public ResponseApi findOrder(int id) {

        return null;
    }

    @Override
    public ResponseApi findOrderByUsername(String username) {
        return null;
    }

    @Override
    public ResponseApi createOrderDetail(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public ResponseApi deleteOrder(int order) {
        return null;
    }
}
