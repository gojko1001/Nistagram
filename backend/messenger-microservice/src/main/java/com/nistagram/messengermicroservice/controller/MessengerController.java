package com.nistagram.messengermicroservice.controller;

import com.nistagram.messengermicroservice.domain.Message;
import com.nistagram.messengermicroservice.service.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MessengerController {

    private static final String SENDING_URL = "/topic/server-message";

    private final SimpMessagingTemplate template;
    private final IMessageService messageService;

    @Scheduled(fixedRate = 1000)
    public void getMessages(@PathVariable String username) {
        List<Message> messages = messageService.findAllByUsername(username);
        template.convertAndSend(SENDING_URL, messages);
    }
}
