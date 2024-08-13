package com.cilia.sales.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaleResponse {

    private Long id;
    private ClientResponse client;
    private List<ProductResponse> products;
    private LocalDateTime saleDate;
    private BigDecimal totalValue;
    private String status;
}
