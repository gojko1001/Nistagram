package com.xws.nistagrammonolith.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${nistagram.rabbitmq.exchange}")
    private String exchange;

    @Value("${nistagram.rabbitmq.routingkey}")
    private String routingkey;

    public void send(String text) {
        rabbitTemplate.convertAndSend(exchange, routingkey, text);
        System.out.println("Send msg = " + text);
    }

}
