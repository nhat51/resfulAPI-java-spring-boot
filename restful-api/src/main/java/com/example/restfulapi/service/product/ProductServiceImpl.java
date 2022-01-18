package com.example.restfulapi.service.product;

import com.example.restfulapi.entity.Category;
import com.example.restfulapi.entity.Product;
//import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.repository.CategoryRepository;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.RESTPagination;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.*;
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
    public ResponseApi findAll() {
        List<Product> products = productRepository.findAll();
     /*   List<ProductDTO> dto = new ArrayList<>();
        for (Product p : products) {
            dto.add(ProductDTO.convertEntityToDTO(p));
        }*/
        return new ResponseApi(HttpStatus.OK,"Success",products);
    }

    @Override
    public ResponseApi findByCategory(int id) {
        Category category = categoryRepository.getById(id);
        List<Product> list = productRepository.findProductsByCategoryId(category.getId());
        if (list.size() < 0){
            return new ResponseApi(HttpStatus.NOT_FOUND,"cant find any product","");
        }
        return new ResponseApi(HttpStatus.OK,"Success",list);
    }

    @Override
    public ResponseApi findByName(String name) {
        Product product = productRepository.findByName(name);
        return new ResponseApi(HttpStatus.OK,"Success",product);
    }


    @Override
    public ResponseApi getById(int id) {
        Optional<Product> product = productRepository.findById(id);
      /*  if (!product.isPresent()) {
            return new ResponseApi(HttpStatus.NOT_FOUND,"Product not found","");
        }
        ProductDTO dto = ProductDTO.convertEntityToDTO(product.get());*/
        return new ResponseApi(HttpStatus.OK,"Success",product);
    }

    @Override
    public ResponseApi save(Product product) {
        Product exist = productRepository.findByName(product.getName());
        if (exist != null){
            return new ResponseApi(HttpStatus.FOUND,"Product already exist","");
        }
        else {
//            Product product = Product.convertDTOtoEntity(productDTO);
            productRepository.save(product);
            return new ResponseApi(HttpStatus.CREATED,"Success",product);
        }
    }

    @Override
    public ResponseApi delete(int id) {
        Product product = productRepository.getById(id);

        return new ResponseApi(HttpStatus.OK,"Success",product);
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
