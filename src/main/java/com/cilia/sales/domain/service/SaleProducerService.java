package com.cilia.sales.domain.service;

import com.cilia.sales.adapter.config.RabbitMQConfig;
import com.cilia.sales.application.dto.request.SaleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter converter;

    public void sendSale(SaleRequest request) {
        rabbitTemplate.setMessageConverter(converter);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, request);
    }
}
