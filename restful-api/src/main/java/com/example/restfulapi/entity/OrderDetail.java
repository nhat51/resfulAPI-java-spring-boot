package com.example.restfulapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;
    @Column(name = "order_Id",updatable = false,insertable = false)
    private int orderId;
    @Column(name = "product_Id",updatable = false,insertable = false)
    private int productId;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_Id")
    @JsonIgnore
    private Order order;
}
