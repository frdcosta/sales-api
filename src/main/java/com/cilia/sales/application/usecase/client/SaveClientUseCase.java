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

    public Client execute(Long id, Client client) {
        return clientService.getClientById(id).map(clientToUpdate -> {
            clientToUpdate.setName(client.getName());
            clientToUpdate.setAddress(client.getAddress());
            clientToUpdate.setEmail(client.getEmail());
            clientToUpdate.setBirthDate(client.getBirthDate());
            clientToUpdate.setPhone(client.getPhone());

            return clientService.saveClient(clientToUpdate);
        }).orElseThrow();
    }
}
