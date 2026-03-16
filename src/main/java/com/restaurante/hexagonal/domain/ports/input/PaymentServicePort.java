package com.restaurante.hexagonal.domain.ports.input;

import java.util.List;
import java.util.Optional;

import com.restaurante.hexagonal.domain.model.Payment;

public interface PaymentServicePort {

    Payment createPayment(Payment payment);

    Optional<Payment> getPaymentById(Long id);

    List<Payment> getAllPayments();

    List<Payment> getPaymentsByOrderId(Long orderId);

    Payment updatePayment(Long id, Payment payment);

    Payment patchPayment(Long id, Payment payment);

    void deletePayment(Long id);
    
}
