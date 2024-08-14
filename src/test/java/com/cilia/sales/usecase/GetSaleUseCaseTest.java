package com.cilia.sales.usecase;

import com.cilia.sales.application.usecase.sale.GetSaleUseCase;
import com.cilia.sales.domain.entity.Sale;
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
class GetSaleUseCaseTest {

    @Mock
    private SaleService saleService;

    @InjectMocks
    private GetSaleUseCase getSaleUseCase;

    private static final Sale SALE_1 = Sale.builder().id(1L).build();
    private static final Sale SALE_2 = Sale.builder().id(2L).build();
    private static final List<Sale> SALES = List.of(SALE_1, SALE_2);

    @Test
    void testExecute_ReturnsAllSales() {
        when(saleService.getAllSales()).thenReturn(SALES);

        List<Sale> result = getSaleUseCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(saleService, times(1)).getAllSales();
    }

    @Test
    void testExecute_WithId_ReturnsSale() {
        when(saleService.getSaleById(1L)).thenReturn(Optional.of(SALE_1));

        Sale result = getSaleUseCase.execute(1L);

        assertNotNull(result);
        assertEquals(SALE_1, result);
        verify(saleService, times(1)).getSaleById(1L);
    }

    @Test
    void testExecute_WithId_SaleNotFound_ThrowsException() {
        when(saleService.getSaleById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            getSaleUseCase.execute(1L);
        });

        assertEquals("Sale not found!", exception.getMessage());
        verify(saleService, times(1)).getSaleById(1L);
    }
}
