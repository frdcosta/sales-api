package com.cilia.sales.service;

import com.cilia.sales.domain.entity.Client;
import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.repository.SaleRepository;
import com.cilia.sales.domain.service.SaleService;
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
public class GetSaleServiceTest {

    @Mock
    private SaleRepository repository;

    @InjectMocks
    private SaleService service;

    private static final Client CLIENT_1 = Client.builder().id(1L).name("Client1").build();
    private static final Sale SALE_1 = Sale.builder().id(1L).build();
    private static final Sale SALE_2 = Sale.builder().id(2L).build();
    private static final List<Sale> SALES = List.of(SALE_1, SALE_2);

    @Test
    void testGetAllSales() {
        when(repository.findAll()).thenReturn(SALES);
        List<Sale> result = service.getAllSales();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetSaleById() {
        when(repository.findById(1L)).thenReturn(Optional.of(SALE_1));

        Optional<Sale> result = service.getSaleById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetSaleByIdThrowsException() {
        when(repository.findById(1L)).thenThrow(new RuntimeException("DB error"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getSaleById(1L);
        });

        assertEquals("Repository problem at SaleRepository::findById", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testSaveSale() {
        SALE_1.setClient(CLIENT_1);
        when(repository.save(SALE_1)).thenReturn(SALE_1);

        Sale result = service.saveSale(SALE_1);

        assertEquals("Client1", result.getClient().getName());
        verify(repository, times(1)).save(SALE_1);
    }

    @Test
    void testSaveSaleThrowsException() {
        SALE_1.setClient(CLIENT_1);
        when(repository.save(SALE_1)).thenThrow(new RuntimeException("DB error"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.saveSale(SALE_1);
        });

        assertEquals("Repository problem at SaleRepository::save", exception.getMessage());
        verify(repository, times(1)).save(SALE_1);
    }


}
