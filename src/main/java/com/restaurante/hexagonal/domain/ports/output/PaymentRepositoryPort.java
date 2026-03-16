package com.restaurante.hexagonal.domain.ports.output;

import java.util.List;
import java.util.Optional;

import com.restaurante.hexagonal.domain.model.Payment;

public interface PaymentRepositoryPort {

    Payment save(Payment payment);

    Optional<Payment> findById(Long id);

    List<Payment> findAll();

    List<Payment> findByOrderId(Long orderId);

    Payment update(Long id, Payment payment);

    Payment patch(Long id, Payment payment);

    void deleteById(Long id);

}