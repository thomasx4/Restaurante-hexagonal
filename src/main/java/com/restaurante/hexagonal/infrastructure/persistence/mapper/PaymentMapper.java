package com.restaurante.hexagonal.infrastructure.persistence.mapper;

import com.restaurante.hexagonal.domain.model.Payment;
import com.restaurante.hexagonal.infrastructure.persistence.entity.PaymentEntity;

public class PaymentMapper {

    // Convertir Entity -> Model
    public static Payment toDomain(PaymentEntity entity) {

        if (entity == null) {
            return null;
        }

        Payment payment = new Payment();

        payment.setId(entity.getId());
        payment.setOrderId(entity.getOrderId());

        // Float -> Double
        if (entity.getAmount() != null) {
            payment.setAmount(entity.getAmount().doubleValue());
        }

        payment.setPaymentMethod(entity.getPaymentMethod());
        payment.setPaymentDate(entity.getPaymentDate());

        // status no existe en entity
        payment.setStatus(null);

        return payment;
    }

    // Convertir Model -> Entity
    public static PaymentEntity toEntity(Payment payment) {

        if (payment == null) {
            return null;
        }

        PaymentEntity entity = new PaymentEntity();

        entity.setId(payment.getId());
        entity.setOrderId(payment.getOrderId());

        // Double -> Float
        if (payment.getAmount() != null) {
            entity.setAmount(payment.getAmount().floatValue());
        }

        entity.setPaymentMethod(payment.getPaymentMethod());
        entity.setPaymentDate(payment.getPaymentDate());

        // reference no existe en model
        entity.setReference(null);

        return entity;
    }
}