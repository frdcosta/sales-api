package com.cilia.sales.adapter.controller;

import com.cilia.sales.adapter.mapper.SaleMapper;
import com.cilia.sales.application.dto.request.SaleRequest;
import com.cilia.sales.application.dto.request.SaleUpdateRequest;
import com.cilia.sales.application.dto.response.SaleResponse;
import com.cilia.sales.application.usecase.sale.DeleteSaleUseCase;
import com.cilia.sales.application.usecase.sale.GetSaleUseCase;
import com.cilia.sales.application.usecase.sale.ProduceSaleUseCase;
import com.cilia.sales.application.usecase.sale.SaveSaleUseCase;
import com.cilia.sales.domain.service.SaleProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final GetSaleUseCase getSaleUseCase;
    private final SaveSaleUseCase saveSaleUseCase;
    private final SaleMapper mapper;
    private final ProduceSaleUseCase produceSaleUseCase;

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        return ResponseEntity.ok(mapper.toResponseList(getSaleUseCase.execute()));
    }

    @PostMapping
    public ResponseEntity<Void> createSale(@Valid @RequestBody SaleRequest request) {
        produceSaleUseCase.execute(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getSaleById(@PathVariable Long id) {
        return getSaleUseCase.execute(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> updateSale(@PathVariable Long id, @Valid @RequestBody SaleUpdateRequest updateRequest) {
        var saleDetails = mapper.fromUpdateRequest(updateRequest);
        return ResponseEntity.ok(mapper.toResponse(saveSaleUseCase.execute(id, saleDetails)));
    }
}
