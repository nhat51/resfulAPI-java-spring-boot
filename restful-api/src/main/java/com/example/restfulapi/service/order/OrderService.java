package com.example.restfulapi.service.order;

import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entity.OrderDetail;
import com.example.restfulapi.response.ResponseApi;

public interface OrderService {
    ResponseApi listOrder();
    Order createOrder(String access_token,Order order);
    ResponseApi findOrder(int id);
    ResponseApi findOrderByUsername(String username);
    ResponseApi createOrderDetail(OrderDetail orderDetail);
    ResponseApi deleteOrder(int order);

}
