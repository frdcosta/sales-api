package com.cilia.sales.controller;

import com.cilia.sales.adapter.config.JwtRequestFilter;
import com.cilia.sales.adapter.controller.ProductController;
import com.cilia.sales.adapter.mapper.ProductMapper;
import com.cilia.sales.application.dto.request.ProductRequest;
import com.cilia.sales.application.dto.response.ProductResponse;
import com.cilia.sales.application.usecase.product.DeleteProductUseCase;
import com.cilia.sales.application.usecase.product.GetProductUseCase;
import com.cilia.sales.application.usecase.product.SaveProductUseCase;
import com.cilia.sales.domain.entity.Product;
import com.cilia.sales.domain.service.UserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private ProductController productController;

    @MockBean
    private DeleteProductUseCase deleteProductUseCase;

    @MockBean
    private GetProductUseCase getProductUseCase;

    @MockBean
    private SaveProductUseCase saveProductUseCase;

    @MockBean
    private ProductMapper mapper;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(deleteProductUseCase, getProductUseCase, saveProductUseCase, mapper);
    }

    private static final Product PRODUCT_1 = Product.builder().id(1L).name("Product1").build();
    private static final ProductResponse PRODUCT_RESPONSE = ProductResponse.builder().build();
    private static final ProductRequest PRODUCT_REQUEST = ProductRequest.builder().build();

    @Test
    void testGetAllProducts() {
        when(getProductUseCase.execute()).thenReturn(Collections.singletonList(PRODUCT_1));
        when(mapper.toResponseList(anyList())).thenReturn(Collections.singletonList(PRODUCT_RESPONSE));

        ResponseEntity<List<ProductResponse>> response = productController.getAllProducts();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testCreateProduct() {
        when(saveProductUseCase.execute(any(Product.class))).thenReturn(PRODUCT_1);
        when(mapper.fromRequest(any(ProductRequest.class))).thenReturn(PRODUCT_1);
        when(mapper.toResponse(any(Product.class))).thenReturn(PRODUCT_RESPONSE);

        ResponseEntity<ProductResponse> response = productController.createProduct(PRODUCT_REQUEST);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetProductById() {
        when(getProductUseCase.execute(1L)).thenReturn(PRODUCT_1);
        when(mapper.toResponse(any(Product.class))).thenReturn(PRODUCT_RESPONSE);

        ResponseEntity<ProductResponse> response = productController.getProductById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateProduct() {
        when(saveProductUseCase.execute(anyLong(), any(Product.class))).thenReturn(PRODUCT_1);
        when(mapper.fromRequest(any(ProductRequest.class))).thenReturn(PRODUCT_1);
        when(mapper.toResponse(any(Product.class))).thenReturn(PRODUCT_RESPONSE);

        ResponseEntity<ProductResponse> response = productController.updateProduct(1L, PRODUCT_REQUEST);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        doNothing().when(deleteProductUseCase).execute(productId);

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
    }
}

