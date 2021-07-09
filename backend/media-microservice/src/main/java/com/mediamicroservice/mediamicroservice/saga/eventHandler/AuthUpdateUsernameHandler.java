package com.mediamicroservice.mediamicroservice.saga.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediamicroservice.mediamicroservice.saga.event.GetUsernameEvent;
import com.mediamicroservice.mediamicroservice.saga.event.UpdateUsernameEvent;
import com.mediamicroservice.mediamicroservice.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class AuthUpdateUsernameHandler {
    private final static String AUTH_UPDATE_QUEUE = "auth-update-username-queue";

    private final IUserService userService;

    @RabbitListener(queues = AUTH_UPDATE_QUEUE)
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
