package com.cilia.sales.adapter.controller;

import com.cilia.sales.adapter.mapper.SaleMapper;
import com.cilia.sales.application.dto.request.SaleRequest;
import com.cilia.sales.application.dto.response.SaleResponse;
import com.cilia.sales.application.usecase.sale.DeleteSaleUseCase;
import com.cilia.sales.application.usecase.sale.GetSaleUseCase;
import com.cilia.sales.application.usecase.sale.SaveSaleUseCase;
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
    private final DeleteSaleUseCase deleteSaleUseCase;
    private final SaleMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        return ResponseEntity.ok(mapper.toResponseList(getSaleUseCase.execute()));
    }

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(@Valid @RequestBody SaleRequest sale) {
        return ResponseEntity.ok(mapper.toResponse(saveSaleUseCase.execute(mapper.fromRequest(sale))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getSaleById(@PathVariable Long id) {
        return getSaleUseCase.execute(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> updateSale(@PathVariable Long id, @Valid @RequestBody SaleRequest saleDetails) {
        return getSaleUseCase.execute(id)
                .map(client -> ResponseEntity.ok(mapper.toResponse(saveSaleUseCase.execute(mapper.fromRequest(saleDetails)))))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        deleteSaleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
