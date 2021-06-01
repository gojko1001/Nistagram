package com.mediamicroservice.mediamicroservice.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${media.rabbitmq.queue}")
    public void recievedMessage(String text) {
        System.out.println("Recieved Message From RabbitMQ: " + text);
    }
}
