package com.cilia.sales.application.usecase.product;

import com.cilia.sales.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteProductUseCase {

    private final ProductService productService;

    public void execute(Long id){
        productService.deleteProduct(id);
    }
}
