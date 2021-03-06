package com.mediamicroservice.mediamicroservice.saga;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue cancelUpdateQueue() {
        return new Queue("cancel-update-username-queue");
    }

    @Bean
    public Queue mediaUpdateQueue() {
        return new Queue("media-update-username-queue");
    }

    @Bean
    public Queue authUpdateQueue() {
        return new Queue("auth-update-username-queue");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
