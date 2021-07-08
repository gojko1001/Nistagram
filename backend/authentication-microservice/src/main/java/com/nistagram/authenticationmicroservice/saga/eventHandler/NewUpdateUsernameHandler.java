package com.nistagram.authenticationmicroservice.saga.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nistagram.authenticationmicroservice.saga.event.GetUsernameEvent;
import com.nistagram.authenticationmicroservice.saga.event.UpdateUsernameEvent;
import com.nistagram.authenticationmicroservice.saga.eventListener.CancelUpdateListener;
import com.nistagram.authenticationmicroservice.service.IUserCredentialsService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.MessageFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

import static org.apache.logging.log4j.message.MessageFactory.*;

@Component
@AllArgsConstructor
public class NewUpdateUsernameHandler {
    private final static String NEW_UPDATE_QUEUE = "new-update-username-queue";

    private final IUserCredentialsService userService;
    private final CancelUpdateListener cancelUpdateListener;

    @RabbitListener(queues = NEW_UPDATE_QUEUE)
    public void receiveUserMessage(String message) throws IOException {
        System.out.println(message);
        GetUsernameEvent event = null;

        try {
            event = new ObjectMapper().readValue(message, GetUsernameEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        userService.newUpdateUsername(event);
    }
}
