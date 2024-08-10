package com.cilia.sales.adapter.mapper;

import com.cilia.sales.application.dto.request.ProductRequest;
import com.cilia.sales.application.dto.response.ProductResponse;
import com.cilia.sales.domain.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);
    List<ProductResponse> toResponseList(List<Product> products);
    Product fromRequest(ProductRequest request);
}
