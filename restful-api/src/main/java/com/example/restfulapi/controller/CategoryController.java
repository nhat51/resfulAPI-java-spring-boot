package com.example.restfulapi.controller;

import com.example.restfulapi.entity.Category;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseApi> getAll(){
        return ResponseEntity.ok().body(
                service.findAll()
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseApi> save(@RequestBody Category category){
        return ResponseEntity.ok().body(
                service.save(category)
        );
    }
}
