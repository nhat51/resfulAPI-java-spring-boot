package com.example.restfulapi.service.customer;

import com.example.restfulapi.entity.Customer;
import com.example.restfulapi.response.ResponseApi;

public interface CustomerService {
    ResponseApi finAll();
    ResponseApi save(Customer customer);
    ResponseApi update(int id,Customer customer);
    ResponseApi delete(int id);
    ResponseApi findByUsername(String name);
}
