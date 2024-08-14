package com.cilia.sales.service;

import com.cilia.sales.adapter.config.RabbitMQConfig;
import com.cilia.sales.application.dto.request.SaleRequest;
import com.cilia.sales.domain.service.SaleProducerService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class SaleProducerServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private Jackson2JsonMessageConverter converter;

    @InjectMocks
    private SaleProducerService saleProducerService;

    private static final SaleRequest SALE_REQUEST = SaleRequest.builder().clientId(1L).products(List.of(1L, 2L)).saleDate(LocalDateTime.now()).totalValue(BigDecimal.valueOf(100)).status("PENDENTE").build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendSaleSuccess() {

        doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(SaleRequest.class));

        saleProducerService.sendSale(SALE_REQUEST);

        verify(rabbitTemplate, times(1)).setMessageConverter(converter);
        verify(rabbitTemplate, times(1)).convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, SALE_REQUEST);
    }

    @Test
    void testSendSaleFailure() {

        doThrow(new AmqpException("RabbitMQ exception")).when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(SaleRequest.class));

        Exception exception = assertThrows(AmqpException.class, () -> {
            saleProducerService.sendSale(SALE_REQUEST);
        });

        assertEquals("RabbitMQ exception", exception.getMessage());
        verify(rabbitTemplate, times(1)).setMessageConverter(converter);
        verify(rabbitTemplate, times(1)).convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, SALE_REQUEST);
    }

}
