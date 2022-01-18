package com.example.restfulapi.service.category;

import com.example.restfulapi.entity.Category;
import com.example.restfulapi.response.ResponseApi;

public interface CategoryService {
    ResponseApi findAll();
    ResponseApi findById(int id);
    ResponseApi save(Category category);
    ResponseApi update(int id, Category category);
    ResponseApi delete(int id);
}
