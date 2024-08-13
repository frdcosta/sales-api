package com.cilia.sales.application.usecase.client;

import com.cilia.sales.domain.entity.Client;
import com.cilia.sales.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetClientUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetClientUseCase.class);
    private final ClientService clientService;

    public List<Client> execute() {
        return clientService.getAllClients();
    }

    public Client execute(Long id) {
        return clientService.getClientById(id).orElseThrow( () -> {
            LOGGER.error("Failed to recover client with id {}", id);
            return new IllegalArgumentException("Client not found!");
        });
    }
}
