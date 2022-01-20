package com.example.restfulapi.service.product;

import com.example.restfulapi.entity.Category;
import com.example.restfulapi.entity.Product;
//import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.entityDTO.CategoryDTO;
import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.repository.CategoryRepository;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.RESTPagination;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.specification.ObjectFilter;
import com.example.restfulapi.specification.ProductSpecification;
import com.example.restfulapi.specification.SearchCriteria;
import com.example.restfulapi.util.SQLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
     private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Product> findAll(ObjectFilter filter) {
        Specification spec = Specification.where(null);

        PageRequest paging = PageRequest.of(filter.getPage() - 1, filter.getPageSize());

        if (filter.getName() != null && filter.getName().length() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.NAME, SQLConstant.EQUAL,filter.getName())));
        }
        if (filter.getCategoryId() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.ID,SQLConstant.EQUAL,filter.getCategoryId())));
        }
        if (filter.getMaxPrice() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.MAX_PRICE,SQLConstant.LESS_THAN_OR_EQUAL_TO,filter.getMaxPrice())));
        }
        if (filter.getMinPrice() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.MIN_PRICE,SQLConstant.GREATER_THAN_OR_EQUAL_TO,filter.getMinPrice())));
        }
       if (filter.getId() > 0){
           spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.ID,SQLConstant.EQUAL,filter.getId())));
       }
        return productRepository.findAll(spec,paging);
    }

    @Override
    public ResponseApi findByCategory(String name) {
        Category category = categoryRepository.findCategoryByName(name);
        List<Product> list = productRepository.findProductsByCategoryId(category.getId());
        if (list.size() < 0){
            return new ResponseApi(HttpStatus.NOT_FOUND,"cant find any product","");
        }
        return new ResponseApi(HttpStatus.OK,"Success",list);
    }

    @Override
    public ResponseApi findByName(String name) {
        Product product = productRepository.search(name);
        return new ResponseApi(HttpStatus.OK,"Success",product);
    }


    @Override
    public ResponseApi getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return new ResponseApi(HttpStatus.NOT_FOUND,"Product not found","");
        }
        ProductDTO dto = ProductDTO.convertEntityToDTO(product.get());
        return new ResponseApi(HttpStatus.OK,"Success",dto);
    }



    @Override
    public ResponseApi save(Product product) {
        Product exist = productRepository.findProductByName(product.getName());
        if (exist != null){
            return new ResponseApi(HttpStatus.FOUND,"Product already exist","");
        }
        else {
           Product saved = productRepository.save(product);
            return new ResponseApi(HttpStatus.CREATED,"Success",saved);
        }
    }

    @Override
    public ResponseApi delete(int id) {
        Product product = productRepository.getById(id);
        productRepository.delete(product);
        return new ResponseApi(HttpStatus.OK,"Success","");
    }

    @Override
    public ResponseApi update(int id, Product product) {
        Product exist = productRepository.getById(id);
            exist.setName(product.getName());
            exist.setPrice(product.getPrice());
            exist.setThumbnail(product.getThumbnail());
            exist.setQuantity(product.getQuantity());
            exist.setCategoryId(product.getCategoryId());
        productRepository.save(exist);
            return  new ResponseApi(HttpStatus.OK,"success",product);

    }
}
