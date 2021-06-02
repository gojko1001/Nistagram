package com.mediamicroservice.mediamicroservice.rabbitmq;

import com.mediamicroservice.mediamicroservice.dto.CreateCommentDto;
import com.mediamicroservice.mediamicroservice.service.interfaces.ICommentService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    // TODO: verovatno obrisati
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private ICommentService commentService;

    // TODO: verovatno obrisati
    @Value("${media.rabbitmq.exchange}")
    private String exchange;

    // TODO: verovatno obrisati
    @Value("${media.rabbitmq.routingkey}")
    private String routingkey;

    /*@RabbitListener(queues = "nistagram.queue")
    public String recievedMessage(String text) {
        System.out.println("Recieved Messge From RabbitMQ: " + text);
        return "yes";
    }*/

    @RabbitListener(queues = "createcomment.queue")
    public Object createCommentOnPost(CreateCommentDto createCommentDto) {
        return commentService.createCommentOnPost(createCommentDto);
    }
}
