package com.restaurante.hexagonal.domain.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Payment {

    private Long id;
    private Long orderId;
    private Double amount;
    private String paymentMethod;
    private LocalDate paymentDate;
    private String status;
    
}
