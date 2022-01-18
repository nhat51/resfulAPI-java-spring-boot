package com.example.restfulapi.service.product;

import com.example.restfulapi.entity.Product;
//import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.response.ResponseApi;

import java.util.List;

public interface ProductService {
    ResponseApi findAll();
    ResponseApi findByCategory(int id);
    ResponseApi findByName(String name);
    ResponseApi getById(int id);
    ResponseApi save(Product product);
    ResponseApi delete(int id);
    ResponseApi update(int id, Product product);
}
