package com.nistagram.usermicroservice.saga;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue newUpdateQueue() {
        return new Queue("new-update-username-queue");
    }

    @Bean
    public Queue cancelUpdateQueue() {
        return new Queue("cancel-update-username-queue");
    }

    @Bean
    public Queue doneUpdateQueue() {
        return new Queue("done-update-username-queue");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
