package com.restaurante.hexagonal.application.dto;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
    private boolean available;
}
