package com.nistagram.messengermicroservice.controller;

import com.nistagram.messengermicroservice.service.IMessageService;
import com.nistagram.messengermicroservice.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MessengerController {

    private static final String SENDING_URL = "/topic/server-message";

    //    private final SimpMessagingTemplate template;
    private final IMessageService messageService;

//    @Scheduled(fixedRate = 1000)
//    public void getMessages(@PathVariable String username) {
//        List<Message> messages = messageService.findAllByUsername(username);
//        template.convertAndSend(SENDING_URL, messages);
//    }

    @GetMapping("/{username}")
    public ResponseEntity getAllByUsername(@PathVariable String username) {
        return new ResponseEntity(messageService.findAllByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createMessage(@RequestBody )
}
