package com.nistagram.usermicroservice.saga.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nistagram.usermicroservice.saga.event.GetUsernameEvent;
import com.nistagram.usermicroservice.saga.event.UpdateUsernameEvent;
import com.nistagram.usermicroservice.user.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CancelUpdateHandler {

    private final static String CANCEL_UPDATE_QUEUE = "cancel-update-username-queue";

    private final IUserService userService;

    @RabbitListener(queues = CANCEL_UPDATE_QUEUE)
    public void receiveUserMessage(String message) throws IOException {
        GetUsernameEvent event = null;
        try {
            event = new ObjectMapper().readValue(message, GetUsernameEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        userService.cancelUpdateUsername(event);
    }
}
