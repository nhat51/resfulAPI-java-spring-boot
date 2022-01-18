package com.example.restfulapi.entityDTO;

import com.example.restfulapi.entity.Product;
import com.example.restfulapi.status.ProductStatus;
import com.example.restfulapi.util.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import static com.example.restfulapi.util.ValidationUtil.*;

/*@Data
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

    public static ProductDTO convertEntityToDTO(Product product){

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setThumbnail(product.getThumbnail());
        if (product.getQuantity() == 0){
            dto.setStatus(ProductStatus.UNAVAILABLE);
        }else if (product.getQuantity() > 0){
            dto.setStatus(ProductStatus.AVAILABLE);
        }
        dto.setCategory(CategoryDTO.convertEntityToDTO(product.getCategory()));
        dto.setCategoryId(product.getCategoryId());
        dto.setQuantity(product.getQuantity());
        return dto;
    }
}*/
