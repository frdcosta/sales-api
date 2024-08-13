package com.cilia.sales.adapter.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "salesQueue";
    public static final String EXCHANGE_NAME = "salesExchange";
    public static final String ROUTING_KEY = "sales.routingKey";

    @Bean
    public Queue salesQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange salesExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue salesQueue, DirectExchange salesExchange) {
        return BindingBuilder.bind(salesQueue).to(salesExchange).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
