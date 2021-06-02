package com.mediamicroservice.mediamicroservice.rabbitmq;

import com.mediamicroservice.mediamicroservice.service.interfaces.ICommentService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${media.rabbitmq.exchange}")
    private String exchange;

    @Value("${media.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    private ICommentService commentService;

    @RabbitListener(queues = "nistagram.queue")
    public void recievedMessage(String text) {
        System.out.println("Recieved Messge From RabbitMQ: " + text);

    }
}
