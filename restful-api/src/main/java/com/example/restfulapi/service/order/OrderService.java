package com.example.restfulapi.service.order;

import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entity.OrderDetail;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.specification.ObjectFilter;

public interface OrderService {
    ResponseApi listOrder(ObjectFilter objectFilter);
    Order createOrder(String access_token,Order order);
    ResponseApi findOrder(int id);
    ResponseApi findOrderByCustomerId(int customerId);
    ResponseApi createOrderDetail(OrderDetail orderDetail);
    ResponseApi deleteOrder(int order);

}
