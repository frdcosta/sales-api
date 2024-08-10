package com.cilia.sales.adapter.mapper;

import com.cilia.sales.application.dto.request.ClientRequest;
import com.cilia.sales.application.dto.response.ClientResponse;
import com.cilia.sales.domain.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponse toResponse(Client client);
    List<ClientResponse> toResponseList(List<Client> clients);
    Client fromRequest(ClientRequest request);
}
