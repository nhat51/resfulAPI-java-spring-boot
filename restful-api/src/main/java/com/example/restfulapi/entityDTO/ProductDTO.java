package com.example.restfulapi.entityDTO;

import com.example.restfulapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private double price;
    private String thumbnail;
    private int quantity;
    private int categoryId;

    public static ProductDTO convertEntityToDTO(Product product){
        ProductDTO temp = new ProductDTO();
        temp.setCategoryId(product.getCategoryId());
        temp.setPrice(product.getPrice());
        temp.setThumbnail(product.getThumbnail());
        temp.setQuantity(product.getQuantity());
        temp.setName(product.getName());
        return temp;
    }
}
