package com.example.restfulapi.repository;

import com.example.restfulapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    @Query("select p from Product p where p.name like %:name%")
    Product findByName(String name);
    List<Product> findProductsByCategoryId(int id);
}
