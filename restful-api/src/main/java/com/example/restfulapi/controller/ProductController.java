package com.example.restfulapi.controller;

import com.example.restfulapi.entity.Product;
//import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.RESTPagination;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll(){
        ResponseApi data = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                data
        );
    }

    @RequestMapping(method = RequestMethod.POST,path = "/save")
    public ResponseEntity<ResponseApi> save(@RequestBody Product product){
        return ResponseEntity.accepted().body(
                service.save(product)
        );
    }
}
