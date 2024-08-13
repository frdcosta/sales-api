package com.cilia.sales.adapter.mapper;

import com.cilia.sales.application.dto.request.SaleUpdateRequest;
import com.cilia.sales.application.dto.response.ProductResponse;
import com.cilia.sales.application.dto.response.SaleResponse;
import com.cilia.sales.domain.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    ClientMapper CLIENT_MAPPER = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "status", source = "status")
    Sale fromUpdateRequest(SaleUpdateRequest request);

    default SaleResponse toResponse(Sale sale){
        var products = fromSaleItemToProductResponse(sale);

        return SaleResponse.builder()
                .id(sale.getId())
                .client(CLIENT_MAPPER.toResponse(sale.getClient()))
                .saleDate(sale.getSaleDate())
                .status(sale.getStatus())
                .products(products)
                .totalValue(sale.getTotalValue())
                .build();
    }

    default List<SaleResponse> toResponseList(List<Sale> sales){
        return sales.stream().map(sale -> {
            var products = fromSaleItemToProductResponse(sale);

            return SaleResponse.builder()
                    .id(sale.getId())
                    .client(CLIENT_MAPPER.toResponse(sale.getClient()))
                    .saleDate(sale.getSaleDate())
                    .status(sale.getStatus())
                    .products(products)
                    .totalValue(sale.getTotalValue())
                    .build();
        }).toList();
    }

    private static List<ProductResponse> fromSaleItemToProductResponse(Sale sale) {
        return sale.getSaleItems().stream().map(item ->
                ProductResponse.builder()
                        .id(item.getProduct().getId())
                        .name(item.getProduct().getName())
                        .description(item.getProduct().getDescription())
                        .price(item.getProduct().getPrice())
                        .manufacturingDate(item.getProduct().getManufacturingDate())
                        .barcode(item.getProduct().getBarcode())
                        .build()).toList();
    }
}
