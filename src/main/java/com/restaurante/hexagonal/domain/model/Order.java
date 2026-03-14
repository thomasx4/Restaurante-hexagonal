package com.restaurante.hexagonal.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Order {
    
    private Long id;
    private Long table_id;
    private String customer_name;
    private LocalDate order_date;
    private String status;
    private Float total;
}
