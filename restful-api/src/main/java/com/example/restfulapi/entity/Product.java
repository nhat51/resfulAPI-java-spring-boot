package com.example.restfulapi.entity;

import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.model.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String thumbnail;
    private int quantity;
    private double price;
    @Column(name = "categoryId")
    private int categoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId",nullable = false,referencedColumnName = "id",insertable = false,updatable = false)
    @JsonIgnore
    private Category category;

    public static Product convertDTOtoEntity(ProductDTO productDTO){
        Product temp = new Product();
        temp.setName(productDTO.getName());
        temp.setCategoryId(productDTO.getCategoryId());
        temp.setQuantity(productDTO.getQuantity());
        temp.setThumbnail(productDTO.getThumbnail());
        temp.setPrice(productDTO.getPrice());
        return temp;
    }
}
