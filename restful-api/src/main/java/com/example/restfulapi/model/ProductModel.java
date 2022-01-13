package com.example.restfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private String name;
    private double price;
    private String thumbnail;
    private int quantity;
    private int categoryId;

}
