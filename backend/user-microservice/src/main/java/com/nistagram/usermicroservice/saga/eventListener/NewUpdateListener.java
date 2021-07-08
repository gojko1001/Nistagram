package com.nistagram.usermicroservice.saga.eventListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nistagram.usermicroservice.saga.event.UpdateUsernameEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewUpdateListener {

    private final static String NEW_UPDATE_QUEUE = "new-update-username-queue";

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue = new Queue(NEW_UPDATE_QUEUE);

    public void sendMessageToNewUpdate(UpdateUsernameEvent event){
        String text = "{}";
        try{
            text = new ObjectMapper().writeValueAsString(event);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(queue.getName(), text);
    }
}
