package com.restaurante.hexagonal.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurante.hexagonal.domain.model.Payment;
import com.restaurante.hexagonal.domain.ports.input.PaymentServicePort;
import com.restaurante.hexagonal.domain.ports.output.PaymentRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentServicePort {

    private final PaymentRepositoryPort paymentRepositoryPort;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepositoryPort.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepositoryPort.findById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepositoryPort.findAll();
    }

    @Override
    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return paymentRepositoryPort.findByOrderId(orderId);
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {
        return paymentRepositoryPort.update(id, payment);
    }

    @Override
    public Payment patchPayment(Long id, Payment payment) {
        return paymentRepositoryPort.patch(id, payment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepositoryPort.deleteById(id);
    }
}