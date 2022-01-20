package com.example.restfulapi.entity;

//import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.entityDTO.ProductDTO;
import com.example.restfulapi.status.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String thumbnail;
    private ProductStatus status;
    private int quantity;
    @Column(name = "categoryId")
    private int categoryId;

    @ManyToOne
    @JoinColumn(name = "categoryId",insertable = false,updatable = false)
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderDetail> orderDetails = new HashSet<>();


    public static Product convertDTOtoEntity(ProductDTO dto){
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setThumbnail(dto.getThumbnail());
        entity.setStatus(dto.getStatus());
        entity.setCategoryId(dto.getCategoryId());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }
}
