package com.example.restfulapi.service.product;

import com.example.restfulapi.entity.Product;
import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.model.ProductModel;
import com.example.restfulapi.response.ResponseApi;

import java.util.List;

public interface ProductService {
    ResponseApi findAll();

    ResponseApi getById(int id);
    ResponseApi save(ProductDTO productDTO);
    ResponseApi delete(int id);
    ResponseApi update(int id, ProductDTO product);
}
