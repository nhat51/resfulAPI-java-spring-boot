package com.example.restfulapi.repository;

import com.example.restfulapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Product findByName(String name);
/*
    @Query("select p from Product p where p.categoryId = 1")
    List<Product> findProductsByCategoryId(int id);*/
}