package com.example.restfulapi.controller;

import com.example.restfulapi.entity.Order;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseApi> getOrders(){
        return  ResponseEntity.ok(service.listOrder());
    }
/*    @PostMapping(path = "save")
    public ResponseEntity<?> save(@RequestBody Order order){

        return  ResponseEntity.ok().body(
                service.createOrder(order)
        );
    }*/
}
