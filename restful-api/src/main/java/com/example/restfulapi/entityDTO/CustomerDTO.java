package com.example.restfulapi.entityDTO;

import com.example.restfulapi.entity.Customer;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private int id;
    private String username;
    private String name;
    private String email;
    private String phone;

    public static CustomerDTO convertEntityToDTO(Customer entity){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        return dto;
    }
}
