package com.restaurante.hexagonal.domain.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Payment {
    private Long id;
    private Long order_id;
    private Float amount;
    private String payment_method;
    private LocalDate payment_date;
    private String status;
}
