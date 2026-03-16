package com.restaurante.hexagonal.infrastructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.restaurante.hexagonal.domain.model.Payment;
import com.restaurante.hexagonal.domain.ports.input.PaymentServicePort;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServicePort paymentServicePort;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentServicePort.createPayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentServicePort.getAllPayments();
    }

    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable Long id) {
        return paymentServicePort.getPaymentById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<Payment> getPaymentsByOrderId(@PathVariable Long orderId) {
        return paymentServicePort.getPaymentsByOrderId(orderId);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentServicePort.updatePayment(id, payment);
    }

    @PatchMapping("/{id}")
    public Payment patchPayment(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentServicePort.patchPayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentServicePort.deletePayment(id);
    }
}