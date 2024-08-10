package com.cilia.sales.application.usecase.sale;

import com.cilia.sales.domain.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteSaleUseCase {

    private final SaleService saleService;

    public void deleteSale(Long id){
        saleService.deleteSale(id);
    }
}
