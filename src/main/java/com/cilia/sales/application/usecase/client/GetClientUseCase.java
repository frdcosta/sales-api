package com.cilia.sales.application.usecase.client;

import com.cilia.sales.domain.entity.Client;
import com.cilia.sales.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetClientUseCase {

    private final ClientService clientService;

    public List<Client> execute() {
        return clientService.getAllClients();
    }

    public Optional<Client> execute(Long id) {
        return clientService.getClientById(id);
    }
}
