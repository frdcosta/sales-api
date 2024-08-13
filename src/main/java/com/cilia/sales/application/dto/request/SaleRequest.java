package com.cilia.sales.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleRequest {

    @NotNull(message = "Client is mandatory")
    private Long clientId;

    @NotNull(message = "Products are mandatory")
    @Size(min = 1, message = "There must be at least one product in the sale")
    private List<Long> products;

    @NotNull(message = "Sale date is mandatory")
    private LocalDateTime saleDate;

    @NotNull(message = "Total value is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total value must be positive")
    private BigDecimal totalValue;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "PENDENTE|FINALIZADA|CANCELADA", message = "Status must be either PENDENTE, FINALIZADA, or CANCELADA")
    private String status;
}
