package com.cilia.sales.service;

import com.cilia.sales.domain.entity.Product;
import com.cilia.sales.domain.repository.ProductRepository;
import com.cilia.sales.domain.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private static final Product PRODUCT_1 = Product.builder().id(1L).name("Product1").build();
    private static final Product PRODUCT_2 = Product.builder().id(1L).name("Product2").build();
    private static final List<Product> PRODUCTS = List.of(PRODUCT_1, PRODUCT_2);

    @Test
    void testGetAllProducts() {
        List<Product> productList = Arrays.asList(PRODUCT_1, PRODUCT_2);
        when(repository.findAll()).thenReturn(productList);

        List<Product> result = service.getAllProducts();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        when(repository.findById(1L)).thenReturn(Optional.of(PRODUCT_1));

        Optional<Product> result = service.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("Product1", result.get().getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdThrowsException() {
        when(repository.findById(1L)).thenThrow(new RuntimeException("DB error"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getProductById(1L);
        });

        assertEquals("Repository problem at ProductService::getProductById", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetAllProductsById() {
        List<Long> ids = Arrays.asList(1L, 2L);
        when(repository.findAllById(ids)).thenReturn(PRODUCTS);

        List<Product> result = service.getAllProductsById(ids);

        assertEquals(2, result.size());
        verify(repository, times(1)).findAllById(ids);
    }

    @Test
    void testGetAllProductsByIdThrowsException() {
        List<Long> ids = Arrays.asList(1L, 2L);
        when(repository.findAllById(ids)).thenThrow(new RuntimeException("DB error"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getAllProductsById(ids);
        });

        assertEquals("Repository problem at ProductService::getAllProductsById", exception.getMessage());
        verify(repository, times(1)).findAllById(ids);
    }

    @Test
    void testSaveProduct() {
        Product product = PRODUCT_1;
        when(repository.save(product)).thenReturn(product);

        Product result = service.saveProduct(product);

        assertEquals(product.getName(), result.getName());
        verify(repository, times(1)).save(product);
    }

    @Test
    void testSaveProductThrowsException() {
        when(repository.save(PRODUCT_1)).thenThrow(new RuntimeException("DB error"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.saveProduct(PRODUCT_1);
        });

        assertEquals("Repository problem at ProductService::saveProduct", exception.getMessage());
        verify(repository, times(1)).save(PRODUCT_1);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(repository).deleteById(1L);

        service.deleteProduct(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProductThrowsException() {
        doThrow(new RuntimeException("DB error")).when(repository).deleteById(1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteProduct(1L);
        });

        assertEquals("Repository problem at ProductService::deleteProduct", exception.getMessage());
        verify(repository, times(1)).deleteById(1L);
    }
}
