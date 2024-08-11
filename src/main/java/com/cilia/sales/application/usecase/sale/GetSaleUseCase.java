package com.cilia.sales.application.usecase.sale;

import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetSaleUseCase {

    private final SaleService saleService;

    public List<Sale> execute() {
        return saleService.getAllSales();
    }

    public Optional<Sale> execute(Long id) {
        return saleService.getSaleById(id);
    }
}
