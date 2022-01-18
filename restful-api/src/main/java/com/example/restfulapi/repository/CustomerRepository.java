package com.example.restfulapi.repository;

import com.example.restfulapi.entity.Customer;
import com.example.restfulapi.response.ResponseApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerByUsername(String username);

}
