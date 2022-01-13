package com.example.restfulapi.entityDTO;

import com.example.restfulapi.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private int id;
    private String name;

    public static CategoryDTO categoryDTO(Category category){
        CategoryDTO temp = new CategoryDTO();
        temp.setId(category.getId());
        temp.setName((category.getName()));
        return temp;
    }
}
