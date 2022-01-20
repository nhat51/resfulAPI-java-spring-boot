package com.example.restfulapi.service.product;

import com.example.restfulapi.entity.Product;
//import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.specification.ObjectFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(ObjectFilter filter);
    ResponseApi findByCategory(String name);
    ResponseApi findByName(String name);
    ResponseApi getById(int id);
    ResponseApi save(Product product);
    ResponseApi delete(int id);
    ResponseApi update(int id, Product product);
}
