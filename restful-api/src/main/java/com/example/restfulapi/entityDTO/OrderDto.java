package com.example.restfulapi.entityDTO;

import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entity.OrderDetail;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private int orderId;
    private int customerId;
    private double totalPrice;
    private String paymentStatus;
    private String orderStatus;
    private String message;

    public OrderDto(Order order){
        this.orderId = order.getId();
        this.customerId = order.getCustomerId();
        this.totalPrice = order.getTotalPrice();
        this.orderStatus = order.getStatus();
        this.paymentStatus = order.getPayment_status();
    }
}
