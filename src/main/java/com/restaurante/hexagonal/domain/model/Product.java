package com.restaurante.hexagonal.domain.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean available;
    private Long categoryId;
}
