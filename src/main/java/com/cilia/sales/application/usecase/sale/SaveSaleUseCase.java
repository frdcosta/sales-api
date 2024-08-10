package com.cilia.sales.application.usecase.sale;

import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveSaleUseCase {

    private final SaleService saleService;

    public Sale saveSale(Sale sale){
        return saleService.saveSale(sale);
    }
}
