package com.example.restfulapi.controller;

import com.example.restfulapi.entity.Product;
//import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.RESTPagination;
import com.example.restfulapi.response.RESTResponse;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.service.product.ProductService;
import com.example.restfulapi.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "pageSize",defaultValue = "5") int pageSize,
            @RequestParam(name = "minPrice",defaultValue = "-1") int minPrice,
            @RequestParam(name = "maxPrice",defaultValue = "-1") int maxPrice,
            @RequestParam(name = "categoryId", defaultValue = "-1") int categoryId,
            @RequestParam(name = "name", required = false) String name
    ){
        ObjectFilter filter = ObjectFilter.ObjectFilterBuilder.anObjectFilter()
                .withCategoryId(categoryId)
                .withNameProduct(name)
                .withPage(page)
                .withMinPrice(minPrice)
                .withMaxPrice(maxPrice)
                .withPageSize(pageSize)
                .build();
        Page<Product> productPage = service.findAll(filter);
        if (productPage.getContent().size() == 0){
            return new ResponseEntity<>(
                    new RESTResponse.Success().setMessage("Product list is empty").build(),HttpStatus.OK);
        }
        List<ProductDTO> dto = new ArrayList<>();
        for (Product p : productPage.getContent()) {
            ProductDTO productDTO = ProductDTO.convertEntityToDTO(p);
            dto.add(productDTO);
        }
       return new ResponseEntity<>(
               new RESTResponse.Success()
                       .addData(dto)
                       .setPagination(new RESTPagination(productPage.getNumber() - 1,productPage.getSize(),productPage.getTotalPages(),productPage.getTotalElements()))
                       .setStatus(HttpStatus.OK.value())
                       .build(),HttpStatus.OK
       );
    }

    @RequestMapping(method = RequestMethod.POST,path = "/save")
    public ResponseEntity<ResponseApi> save(@RequestBody Product product){
        return ResponseEntity.accepted().body(
                service.save(product)
        );
    }

    @RequestMapping(method = RequestMethod.PUT,path = "update/{id}")
    public ResponseEntity<ResponseApi> update(@PathVariable int id,@RequestBody Product product){
        return ResponseEntity.accepted().body(
                service.update(id,product)
        );
    }

    @RequestMapping(method = RequestMethod.GET,path = "findByCategory/{categoryName}")
    private ResponseEntity<ResponseApi> findByCategoryName(@PathVariable String categoryName){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.OK,"success",service.findByCategory(categoryName))
        );
    }

    @RequestMapping(method = RequestMethod.GET,path = "findByName/{productName}")
    private ResponseEntity<ResponseApi> findByName(@PathVariable String productName){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.OK,"success",service.findByName(productName))
        );
    }

    @RequestMapping(method = RequestMethod.GET,path = "detail/{id}")
    private ResponseEntity<ResponseApi> findById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.OK,"success",service.getById(id))
        );
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "delete/{id}")
    private ResponseEntity<ResponseApi> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseApi(HttpStatus.OK,"success","")
        );
    }
}
