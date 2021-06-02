package com.xws.nistagrammonolith.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    // TODO: verovatno obrisati
    @Value("${nistagram.rabbitmq.exchange}")
    private String exchange;

    // TODO: verovatno obrisati
    @Value("${nistagram.rabbitmq.routingkey}")
    private String routingkey;

    public void send(String text) {
        String result = (String) rabbitTemplate.convertSendAndReceive("createcomment.queue", text);
        System.out.println("Response msg = " + result);
    }

}
