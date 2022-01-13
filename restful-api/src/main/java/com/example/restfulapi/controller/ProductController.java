package com.example.restfulapi.controller;

import com.example.restfulapi.entity.Product;
import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.model.ProductModel;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.service.product.ProductService;
import com.example.restfulapi.specification.SearchCriteria;
import com.example.restfulapi.specification.Specification;
import com.example.restfulapi.util.SQLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    ProductRepository repository;

    //tìm list product theo phân trang và điều kiện
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseApi> getAll(@RequestParam(required = false) String name,
                                              @RequestParam(required = false,defaultValue = "0") int id,
                                              @RequestParam(required = false,defaultValue = "0") double price){
        Specification spec = null;
        if (name != null) {spec  = new Specification(new SearchCriteria("name", SQLConstant.LIKE, name));}
        if (id != 0){spec  = new Specification(new SearchCriteria("id", SQLConstant.GREATER_THAN_OR_EQUAL_TO, id));}
        if (price != 0){spec  = new Specification(new SearchCriteria("price", SQLConstant.GREATER_THAN_OR_EQUAL_TO, price));}
        Pageable page = PageRequest.of(0, 3);//(page hiện tại, limit)
        Page<?> list = repository.findAll(spec,page);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.OK,"success",list)
        );
    }

    //lưu product vào database
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseApi> save(@RequestBody ProductDTO productDTO){
        service.save(productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.CREATED,"created",productDTO)
        );
    }

    @RequestMapping(method = RequestMethod.GET,path ="{id}")
    public ResponseEntity<ResponseApi> findById( @PathVariable(required = false) int id){
        ResponseApi data = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.OK,"founded",data)
        );
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "{id}")
    public ResponseEntity<ResponseApi> delete(@RequestParam int id){
        ResponseApi data = service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.OK,"Delete success",data)
        );
    }

    @RequestMapping(method = RequestMethod.PUT,path = "{id}")
    public ResponseEntity<ResponseApi> update(@PathVariable(value = "id") int id,@RequestBody ProductDTO productDTO){
        ResponseApi data = service.update(id,productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.OK,"Delete success",data)
        );
    }
}
