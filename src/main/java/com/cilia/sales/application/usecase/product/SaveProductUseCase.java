package com.cilia.sales.application.usecase.product;

import com.cilia.sales.domain.entity.Product;
import com.cilia.sales.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveProductUseCase {

    private final ProductService productService;

    public Product execute(Product product){
        return productService.saveProduct(product);
    }
}
