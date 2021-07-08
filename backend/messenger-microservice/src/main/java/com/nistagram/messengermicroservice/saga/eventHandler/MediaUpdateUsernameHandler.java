package com.nistagram.messengermicroservice.saga.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nistagram.messengermicroservice.saga.event.GetUsernameEvent;
import com.nistagram.messengermicroservice.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class MediaUpdateUsernameHandler {
    private final static String MEDIA_UPDATE_QUEUE = "media-update-username-queue";

    private final IUserService userService;

    @RabbitListener(queues = MEDIA_UPDATE_QUEUE)
    public void receiveUserMessage(String message) throws IOException {
        GetUsernameEvent event = null;
        try {
            event = new ObjectMapper().readValue(message, GetUsernameEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        userService.updateUsername(event);
    }
}
