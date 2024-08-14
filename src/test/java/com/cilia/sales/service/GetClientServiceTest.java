package com.cilia.sales.service;

import com.cilia.sales.domain.entity.Client;
import com.cilia.sales.domain.repository.ClientRepository;
import com.cilia.sales.domain.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetClientServiceTest {


    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    private static final Client CLIENT_1 = Client.builder().id(1L).name("Client1").build();
    private static final Client CLIENT_2 = Client.builder().id(1L).name("Client2").build();
    private static final List<Client> CLIENTS = List.of(CLIENT_1, CLIENT_2);

    @Test
    void testGetAllClients() {
        when(repository.findAll()).thenReturn(CLIENTS);

        List<Client> result = service.getAllClients();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetClientById() {
        when(repository.findById(1L)).thenReturn(Optional.of(CLIENT_1));

        Optional<Client> result = service.getClientById(1L);

        assertTrue(result.isPresent());
        assertEquals("Client1", result.get().getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetClientByIdThrowsException() {
        when(repository.findById(1L)).thenThrow(new RuntimeException("DB error"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getClientById(1L);
        });

        assertEquals("Repository problem at ClientRepository::getClientById", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testSaveClient() {
        when(repository.save(CLIENT_1)).thenReturn(CLIENT_1);

        Client result = service.saveClient(CLIENT_1);

        assertEquals(CLIENT_1.getName(), result.getName());
        verify(repository, times(1)).save(CLIENT_1);
    }

    @Test
    void testSaveClientThrowsException() {
        when(repository.save(CLIENT_1)).thenThrow(new RuntimeException("DB error"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.saveClient(CLIENT_1);
        });

        assertEquals("Repository problem at ClientRepository::saveClient", exception.getMessage());
        verify(repository, times(1)).save(CLIENT_1);
    }

    @Test
    void testDeleteClient() {
        doNothing().when(repository).deleteById(1L);

        service.deleteClient(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteClientThrowsException() {
        doThrow(new RuntimeException("DB error")).when(repository).deleteById(1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteClient(1L);
        });

        assertEquals("Repository problem at ClientRepository::deleteClient", exception.getMessage());
        verify(repository, times(1)).deleteById(1L);
    }

}
