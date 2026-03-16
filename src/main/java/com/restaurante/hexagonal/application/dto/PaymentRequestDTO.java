package com.restaurante.hexagonal.application.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    
    private Long order_id;
    private Float amount;
    private String payment_method;
    private String status;

}
