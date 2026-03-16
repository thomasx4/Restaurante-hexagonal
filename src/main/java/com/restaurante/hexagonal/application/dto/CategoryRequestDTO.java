package com.restaurante.hexagonal.application.dto;

import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String name;
    private String description;
}