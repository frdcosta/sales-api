package com.cilia.sales.domain.service;

import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleService.class);
    private final SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        LOGGER.info("Retrieving all sales...");
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        LOGGER.info("Retrieving sale with id: {}", id);
        try {
            return saleRepository.findById(id);
        } catch (Exception e){
            LOGGER.error("Fail to retrive sale by id: {}", id);
            throw new IllegalArgumentException("Repository problem at SaleRepository::findById");
        }
    }

    public Sale saveSale(Sale sale) {
        LOGGER.info("Saving sale from client: {}", sale.getClient().getName());
        try {
            return saleRepository.save(sale);
        } catch (Exception e){
            LOGGER.error("Fail to save sale: {}", sale);
            throw new IllegalArgumentException("Repository problem at SaleRepository::save");
        }

    }
}
