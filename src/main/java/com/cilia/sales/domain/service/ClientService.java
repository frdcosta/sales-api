package com.cilia.sales.domain.service;

import com.cilia.sales.domain.entity.Client;
import com.cilia.sales.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        LOGGER.info("Retrieving all clients...");
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        LOGGER.info("Retrieving client with id: {}", id);
        try {
            return clientRepository.findById(id);
        } catch (Exception e){
            LOGGER.error("Fail to retrive client by id: {}", id);
            throw new IllegalArgumentException("Repository problem at ClientRepository::getClientById");
        }
    }

    public Client saveClient(Client client) {
        LOGGER.info("Saving client: {}", client.getName());
        try {
            return clientRepository.save(client);
        } catch (Exception e){
            LOGGER.error("Fail to save client: {}, id: {}", client.getName(), client.getId());
            throw new IllegalArgumentException("Repository problem at ClientRepository::saveClient");
        }
    }

    public void deleteClient(Long id) {
        LOGGER.info("Deleting client with id: {}", id);
        try {
            clientRepository.deleteById(id);
        } catch (Exception e){
            LOGGER.error("Fail to delete client with id: {}", id);
            throw new IllegalArgumentException("Repository problem at ClientRepository::deleteClient");
        }
    }
}
