package com.restaurante.hexagonal.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_id", nullable = false)
    private Long table_id;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "order_date")
    private LocalDate order_date;

    @Column(name = "status")
    private String status;

    @Column(name = "total")
    private Float total;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;
}
