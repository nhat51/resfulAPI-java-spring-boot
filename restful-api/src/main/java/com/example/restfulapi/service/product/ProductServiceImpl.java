package com.example.restfulapi.service.product;

import com.example.restfulapi.entity.Product;
import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.model.ProductModel;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
     private ProductRepository repository;
    @Override
    public ResponseApi findAll() {
        List<Product> products = repository.findAll();
        List<ProductDTO> dto = new ArrayList<>();
        for (Product p : products) {
            dto.add(ProductDTO.convertEntityToDTO(p));
        }
        return new ResponseApi(HttpStatus.OK,"Success",dto);
    }


    @Override
    public ResponseApi getById(int id) {
        Optional<Product> product = repository.findById(id);
        if (!product.isPresent()) {
            return new ResponseApi(HttpStatus.NOT_FOUND,"Product not found","");
        }
        ProductDTO dto = ProductDTO.convertEntityToDTO(product.get());
        return new ResponseApi(HttpStatus.OK,"Success",dto);
    }

    @Override
    public ResponseApi save(ProductDTO productDTO) {
        Product exist = repository.findByName(productDTO.getName());
        if (exist != null){
            return new ResponseApi(HttpStatus.FOUND,"Product already exist","");
        }
        else {
            Product product = Product.convertDTOtoEntity(productDTO);
            repository.save(product);
            return new ResponseApi(HttpStatus.CREATED,"Success",product);
        }
    }

    @Override
    public ResponseApi delete(int id) {
        Product product = repository.getById(id);

        return new ResponseApi(HttpStatus.OK,"Success",product);
    }

    @Override
    public ResponseApi update(int id, ProductDTO productDTO) {
        Product exist = repository.getById(id);
            exist.setName(productDTO.getName());
            exist.setPrice(productDTO.getPrice());
            exist.setThumbnail(productDTO.getThumbnail());
            exist.setQuantity(productDTO.getQuantity());
            exist.setCategoryId(productDTO.getCategoryId());
            repository.save(exist);
            return  new ResponseApi(HttpStatus.OK,"success",productDTO);

    }
}
