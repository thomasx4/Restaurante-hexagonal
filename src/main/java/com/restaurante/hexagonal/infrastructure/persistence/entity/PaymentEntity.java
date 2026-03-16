package com.restaurante.hexagonal.infrastructure.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
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
    private Long id;

    // ID de la orden asociada
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    // monto del pago
    @Column(name = "amount")
    private Float amount;

    // método de pago (card, cash, etc)
    @Column(name = "payment_method")
    private String paymentMethod;

    // fecha del pago
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    // referencia de pago
    @Column(name = "reference")
    private String reference;

    // relación con la tabla orders
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderEntity order;
}