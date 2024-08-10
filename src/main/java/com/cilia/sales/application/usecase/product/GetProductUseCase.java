package com.cilia.sales.application.usecase.product;

import com.cilia.sales.domain.entity.Product;
import com.cilia.sales.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetProductUseCase {

    private final ProductService productService;

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public Optional<Product> getProductById(Long id) {
        return productService.getProductById(id);
    }
}
