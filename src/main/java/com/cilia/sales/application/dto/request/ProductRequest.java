package com.cilia.sales.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductRequest {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Size(max = 255, message = "Description can have a maximum of 255 characters")
    private String description;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;

    @NotBlank(message = "Barcode is mandatory")
    @Pattern(regexp = "^\\d{13}$", message = "Barcode must be 13 numeric digits")
    private String barcode;

    @NotNull(message = "Manufacturing date is mandatory")
    @Past(message = "Manufacturing date must be in the past")
    private LocalDate manufacturingDate;
}
