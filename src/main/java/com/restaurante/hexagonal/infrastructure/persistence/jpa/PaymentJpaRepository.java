package com.restaurante.hexagonal.infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.hexagonal.infrastructure.persistence.entity.PaymentEntity;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long>{

    // Buscar pagos por id de orden
    List<PaymentEntity> findByOrderId(Long orderId);
}


