package com.cilia.sales.application.usecase.sale;

import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.service.SaleProducerService;
import com.cilia.sales.domain.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetSaleUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleProducerService.class);
    private final SaleService saleService;

    public List<Sale> execute() {
        return saleService.getAllSales();
    }

    public Sale execute(Long id) {
        return saleService.getSaleById(id).orElseThrow(() -> {
            LOGGER.error("Failed to recover sale with id {}", id);
            return new IllegalArgumentException("Sale not found!");
        });
    }
}
