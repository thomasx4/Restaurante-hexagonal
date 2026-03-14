package com.restaurante.hexagonal.infrastructure.persistence.entity;

import com.restaurante.hexagonal.domain.model.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long order_id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "payment_method")
    private String payment_method;

    @Column(name = "payment_date")
    private Long payment_date;

    @Column(name = "reference")
    private String reference;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
