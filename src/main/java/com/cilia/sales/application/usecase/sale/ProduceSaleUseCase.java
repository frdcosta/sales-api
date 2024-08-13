package com.cilia.sales.application.usecase.sale;

import com.cilia.sales.application.dto.request.SaleRequest;
import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.service.SaleProducerService;
import com.cilia.sales.domain.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProduceSaleUseCase {

    private final SaleProducerService service;

    public void execute(SaleRequest request){
        service.sendSale(request);
    }
}
