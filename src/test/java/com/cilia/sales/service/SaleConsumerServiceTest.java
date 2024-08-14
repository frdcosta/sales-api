package com.cilia.sales.service;

import com.cilia.sales.application.dto.request.SaleRequest;
import com.cilia.sales.domain.entity.Client;
import com.cilia.sales.domain.entity.Product;
import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.repository.SaleRepository;
import com.cilia.sales.domain.service.ClientService;
import com.cilia.sales.domain.service.ProductService;
import com.cilia.sales.domain.service.SaleConsumerService;
import com.cilia.sales.domain.service.SaleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaleConsumerServiceTest {

    @Mock
    private SaleService saleService;

    @Mock
    private ClientService clientService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private SaleConsumerService saleConsumerService;

    private static final Product PRODUCT_1 = Product.builder().id(1L).name("Product1").build();
    private static final Product PRODUCT_2 = Product.builder().id(1L).name("Product2").build();
    private static final List<Product> PRODUCTS = List.of(PRODUCT_1, PRODUCT_2);
    private static final Client CLIENT_1 = Client.builder().id(1L).name("Client1").build();
    private static final Sale SALE_1 = Sale.builder().id(1L).build();
    private static final SaleRequest SALE_REQUEST = SaleRequest.builder().clientId(1L).products(List.of(1L, 2L)).saleDate(LocalDateTime.now()).totalValue(BigDecimal.valueOf(100)).status("PENDENTE").build();

    @Test
    void testConsumeSaleSuccess() {
        SALE_1.setClient(CLIENT_1);
        SALE_1.setTotalValue(BigDecimal.valueOf(100));

        when(clientService.getClientById(1L)).thenReturn(Optional.of(CLIENT_1));
        when(productService.getAllProductsById(List.of(1L, 2L))).thenReturn(PRODUCTS);
        when(saleService.saveSale(any(Sale.class))).thenReturn(SALE_1);

        saleConsumerService.consumeSale(SALE_REQUEST);

        verify(clientService, times(1)).getClientById(1L);
        verify(productService, times(1)).getAllProductsById(List.of(1L, 2L));
        verify(saleService, times(1)).saveSale(any(Sale.class));
    }

    @Test
    void testConsumeSaleClientNotFound() {

        when(clientService.getClientById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            saleConsumerService.consumeSale(SALE_REQUEST);
        });

        assertEquals("Client not found!", exception.getMessage());
        verify(clientService, times(1)).getClientById(1L);
        verify(productService, times(0)).getAllProductsById(anyList());
        verify(saleService, times(0)).saveSale(any(Sale.class));
    }
}
