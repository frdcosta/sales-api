package com.cilia.sales.domain.service;

import com.cilia.sales.adapter.config.RabbitMQConfig;
import com.cilia.sales.application.dto.request.SaleRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleProducerService.class);
    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter converter;

    public void sendSale(SaleRequest request) {
        LOGGER.info("Sending Sale {} to queue.", request.toString());
        try{
            rabbitTemplate.setMessageConverter(converter);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, request);
        } catch (AmqpException e) {
            LOGGER.error("Fail to send Sale {} to queue.", request);
            throw e;
        }
    }
}
