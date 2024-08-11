package com.cilia.sales.application.usecase.client;

import com.cilia.sales.domain.entity.Client;
import com.cilia.sales.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveClientUseCase {

    private final ClientService clientService;

    public Client execute(Client client){
        return clientService.saveClient(client);
    }
}
