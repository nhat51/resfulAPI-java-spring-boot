package com.example.restfulapi.entityDTO;

import com.example.restfulapi.status.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int id;
    @NotEmpty(message = "Please fill product name")
    private String name;
    private double price;
    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",message = "thumbnail must be a url")
    private String thumbnail;
    private ProductStatus status;
    private int categoryId;
    private int quantity;
    private CategoryDTO category;

}
