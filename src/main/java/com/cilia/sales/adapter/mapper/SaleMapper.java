package com.cilia.sales.adapter.mapper;

import com.cilia.sales.application.dto.request.SaleRequest;
import com.cilia.sales.application.dto.response.SaleResponse;
import com.cilia.sales.domain.entity.Sale;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    SaleResponse toResponse(Sale sale);
    List<SaleResponse> toResponseList(List<Sale> sales);
    Sale fromRequest(SaleRequest request);
}
