package com.cilia.sales.adapter.controller;

import com.cilia.sales.adapter.mapper.ProductMapper;
import com.cilia.sales.application.dto.request.ProductRequest;
import com.cilia.sales.application.dto.response.ProductResponse;
import com.cilia.sales.application.usecase.product.DeleteProductUseCase;
import com.cilia.sales.application.usecase.product.GetProductUseCase;
import com.cilia.sales.application.usecase.product.SaveProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final DeleteProductUseCase deleteProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final SaveProductUseCase saveProductUseCase;
    private final ProductMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(mapper.toResponseList(getProductUseCase.execute()));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(mapper.toResponse(saveProductUseCase.execute(mapper.fromRequest(product))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return getProductUseCase.execute(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        var productDetails = mapper.fromRequest(request);
        return ResponseEntity.ok(mapper.toResponse(saveProductUseCase.execute(id, productDetails)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        deleteProductUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
