package com.cilia.sales.domain.service;

import com.cilia.sales.adapter.config.RabbitMQConfig;
import com.cilia.sales.adapter.mapper.ProductMapper;
import com.cilia.sales.adapter.mapper.SaleMapper;
import com.cilia.sales.application.dto.request.SaleRequest;
import com.cilia.sales.domain.entity.Sale;
import com.cilia.sales.domain.entity.SaleItem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleConsumerService.class);
    private final SaleService saleService;
    private final ClientService clientService;
    private final ProductService productService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeSale(SaleRequest saleRequest) {
        LOGGER.info("Consuming Sale {} from queue {}", saleRequest.toString(), RabbitMQConfig.QUEUE_NAME);
        var client = clientService.getClientById(saleRequest.getClientId()).orElseThrow(() -> new IllegalArgumentException("Client not found!"));
        var products = productService.getAllProductsById(saleRequest.getProducts());
        var saleToPersist = Sale.builder()
                .saleDate(saleRequest.getSaleDate())
                .client(client)
                .status(saleRequest.getStatus())
                .totalValue(saleRequest.getTotalValue())
                .build();

        var saleItems = products.stream().map(item ->
                SaleItem.builder()
                        .sale(saleToPersist)
                        .product(item)
                        .build()).toList();

        saleToPersist.setSaleItems(saleItems);
        var savedSale = saleService.saveSale(saleToPersist);
        LOGGER.info( "Sale {} created: ", savedSale.toString());
    }
}
