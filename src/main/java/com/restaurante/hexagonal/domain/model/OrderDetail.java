package com.restaurante.hexagonal.domain.model;

import lombok.Data;

@Data
public class OrderDetail {
    private Long id;
    private Long order_id;
    private Long product_id;
    private Integer quantity;
    private Float unit_price;
    private Float subtotal;
}
