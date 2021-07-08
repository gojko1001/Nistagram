package com.nistagram.authenticationmicroservice.saga.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nistagram.authenticationmicroservice.saga.event.GetUsernameEvent;
import com.nistagram.authenticationmicroservice.saga.event.UpdateUsernameEvent;
import com.nistagram.authenticationmicroservice.service.IUserCredentialsService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class UsernameUpdateDoneHandler {
    private final static String DONE_UPDATE_QUEUE = "done-update-username-queue";

    private final IUserCredentialsService userService;

    @RabbitListener(queues = DONE_UPDATE_QUEUE)
    public void receiveUserMessage(String message) throws IOException {
        GetUsernameEvent event = null;
        try {
            event = new ObjectMapper().readValue(message, GetUsernameEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        userService.usernameUpdateDone(event);
    }
}
