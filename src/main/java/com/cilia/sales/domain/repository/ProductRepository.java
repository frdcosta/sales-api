package com.cilia.sales.domain.repository;

import com.cilia.sales.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
