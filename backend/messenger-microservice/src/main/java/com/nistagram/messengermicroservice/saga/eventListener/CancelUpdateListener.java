package com.nistagram.messengermicroservice.saga.eventListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nistagram.messengermicroservice.saga.event.UpdateUsernameEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CancelUpdateListener {
    private final static String CANCEL_UPDATE_QUEUE = "cancel-update-username-queue";

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue = new Queue(CANCEL_UPDATE_QUEUE);

    public void sendMessageToNewUpdate(UpdateUsernameEvent event) {
        String text = "{}";
        try {
            text = new ObjectMapper().writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(queue.getName(), text);
    }
}
