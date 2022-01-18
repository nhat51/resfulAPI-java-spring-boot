package com.example.restfulapi.entityDTO;

import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entity.OrderDetail;
import com.example.restfulapi.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private String shipName;
    private String shipAddress;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private int customerId;
    private double totalPrice;
    private LocalDate created_at;
    private LocalDate updated_at;
    private Set<OrderDetail> orderDetails;

    public static OrderDTO convertEntityToDTO(Order entity){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(entity.getId());
        orderDTO.setOrderDetails(entity.getOrderDetails());
        orderDTO.setCreated_at(entity.getCreated_at());
        orderDTO.setCustomerId(entity.getCustomerId());
        orderDTO.setShipAddress(entity.getShipAddress());
        orderDTO.setShipName(entity.getShipName());
        orderDTO.setUpdated_at(entity.getUpdated_at());
        orderDTO.setTotalPrice(entity.getTotalPrice());
        orderDTO.setStatus(entity.getStatus());
        return orderDTO;
    }

}
