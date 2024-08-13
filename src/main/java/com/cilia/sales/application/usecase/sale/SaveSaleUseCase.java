package com.cilia.sales.application.usecase.sale;

import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveSaleUseCase {

    private final SaleService saleService;

    public Sale execute(Sale sale){
        return saleService.saveSale(sale);
    }

    public Sale execute(Long id, Sale sale){
        return saleService.getSaleById(id).map(saleToUpdate -> {
            saleToUpdate.setStatus(sale.getStatus());
            return saleService.saveSale(saleToUpdate);
        }).orElseThrow();
    }
}
