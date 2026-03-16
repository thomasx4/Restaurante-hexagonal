package com.restaurante.hexagonal.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.restaurante.hexagonal.domain.model.Payment;
import com.restaurante.hexagonal.domain.ports.output.PaymentRepositoryPort;
import com.restaurante.hexagonal.infrastructure.persistence.entity.PaymentEntity;
import com.restaurante.hexagonal.infrastructure.persistence.jpa.PaymentJpaRepository;
import com.restaurante.hexagonal.infrastructure.persistence.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentRepositoryPort {

    private final PaymentJpaRepository paymentJpaRepository;

    // Guardar pago
    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity = PaymentMapper.toEntity(payment);
        PaymentEntity saved = paymentJpaRepository.save(entity);
        return PaymentMapper.toDomain(saved);
    }

    // Buscar por id
    @Override
    public Optional<Payment> findById(Long id) {
        return paymentJpaRepository.findById(id)
                .map(PaymentMapper::toDomain);
    }

    // Obtener todos
    @Override
    public List<Payment> findAll() {
        return paymentJpaRepository.findAll()
                .stream()
                .map(PaymentMapper::toDomain)
                .collect(Collectors.toList());
    }

    // Buscar por orderId
    @Override
    public List<Payment> findByOrderId(Long orderId) {
        return paymentJpaRepository.findByOrderId(orderId)
                .stream()
                .map(PaymentMapper::toDomain)
                .collect(Collectors.toList());
    }

    // Update completo
    @Override
    public Payment update(Long id, Payment payment) {

        Optional<PaymentEntity> existing = paymentJpaRepository.findById(id);

        if (existing.isPresent()) {
            PaymentEntity entity = PaymentMapper.toEntity(payment);
            entity.setId(id);
            return PaymentMapper.toDomain(paymentJpaRepository.save(entity));
        }

        return null;
    }

    // Patch (actualización parcial)
    @Override
    public Payment patch(Long id, Payment payment) {

        Optional<PaymentEntity> optional = paymentJpaRepository.findById(id);

        if (optional.isPresent()) {

            PaymentEntity entity = optional.get();

            if (payment.getAmount() != null)
                entity.setAmount(payment.getAmount().floatValue());

            if (payment.getPaymentMethod() != null)
                entity.setPaymentMethod(payment.getPaymentMethod());

            if (payment.getPaymentDate() != null)
                entity.setPaymentDate(payment.getPaymentDate());

            return PaymentMapper.toDomain(paymentJpaRepository.save(entity));
        }

        return null;
    }

    // Eliminar
    @Override
    public void deleteById(Long id) {
        paymentJpaRepository.deleteById(id);
    }
}