package com.cilia.sales.domain.service;

import com.cilia.sales.domain.entity.Product;
import com.cilia.sales.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getAllProducts() {
        LOGGER.info("Retrieving all products...");
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        LOGGER.info("Retrieving Product with id: {}", id);
        try {
            return productRepository.findById(id);
        } catch (Exception e){
            LOGGER.error("Fail to retrive Product by id: {}", id);
            throw new IllegalArgumentException("Repository problem at ProductService::getProductById");
        }
    }

    public List<Product> getAllProductsById(List<Long> ids){
        LOGGER.info("Retrieving all products with ids: {}", ids);
        try {
            return productRepository.findAllById(ids);
        } catch (Exception e){
            LOGGER.error("Fail to retrieve products: {}", ids);
            throw new IllegalArgumentException("Repository problem at ProductService::getAllProductsById");
        }
    }

    public Product saveProduct(Product product) {
        LOGGER.info("Saving product with id: {}", product.getId());
        try {
            return productRepository.save(product);
        } catch (Exception e){
            LOGGER.error("Fail to save product with id: {}", product.getId());
            throw new IllegalArgumentException("Repository problem at ProductService::saveProduct");
        }

    }

    public void deleteProduct(Long id) {
        LOGGER.info("Deleting product with id: {}", id);
        try {
            productRepository.deleteById(id);
        } catch (Exception e){
            LOGGER.error("Fail to delete product with id: {}", id);
            throw new IllegalArgumentException("Repository problem at ProductService::deleteProduct");
        }
    }
}
