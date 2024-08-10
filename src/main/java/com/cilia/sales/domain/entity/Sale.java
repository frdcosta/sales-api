package com.cilia.sales.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Client client;

    @ManyToMany
    @NotNull
    @Size(min = 1)
    private List<Product> products;

    @NotNull
    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @NotNull
    @Positive
    @Column(name = "total_value")
    private BigDecimal totalValue;

    @NotNull
    private String status;
}
