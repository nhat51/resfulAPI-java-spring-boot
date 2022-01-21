
package com.example.restfulapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Integer productId;
    private String productName;
    private String thumbnail;
    private double unitPrice; // giá tại thời điểm mua, sau đó giá sản phẩm có thể thay đổi.
    private int quantity;

}

