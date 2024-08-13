package com.cilia.sales.application.usecase.product;

import com.cilia.sales.domain.entity.Product;
import com.cilia.sales.domain.service.ProductService;
import com.cilia.sales.domain.service.SaleProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetProductUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleProducerService.class);
    private final ProductService productService;

    public List<Product> execute() {
        return productService.getAllProducts();
    }

    public Product execute(Long id) {
        return productService.getProductById(id).orElseThrow(() -> {
            LOGGER.error("Failed to recover product with id {}", id);
            return new IllegalArgumentException("Product not found!");
        });
    }
}
