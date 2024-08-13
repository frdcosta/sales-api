package com.cilia.sales.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleUpdateRequest {

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "PENDENTE|FINALIZADA|CANCELADA", message = "Status must be either PENDENTE, FINALIZADA, or CANCELADA")
    private String status;
}
