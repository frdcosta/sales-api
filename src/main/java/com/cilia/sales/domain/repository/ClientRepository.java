package com.cilia.sales.domain.repository;

import com.cilia.sales.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
