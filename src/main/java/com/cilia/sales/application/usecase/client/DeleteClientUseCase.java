package com.cilia.sales.application.usecase.client;

import com.cilia.sales.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteClientUseCase {

    private final ClientService clientService;

    public void deleteClient(Long id){
        clientService.deleteClient(id);
    }
}
