package com.restaurante.hexagonal.application.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PaymentResponseDTO {
    
    private Long id;
    private Long order_id;
    private Float amount;
    private String payment_method;
    private LocalDate payment_date;
    private String status;

}
