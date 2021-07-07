//package com.nistagram.messengermicroservice.controller;
//
//import com.nistagram.messengermicroservice.controller.dto.MessageDto;
//import com.nistagram.messengermicroservice.service.interfaces.IMessageService;
//import lombok.AllArgsConstructor;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.annotation.SubscribeMapping;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Controller
//@RestController
//@AllArgsConstructor
//public class WebSocketController {
//
//    private static final String SENDING_URL = "/topic/messages";
//    private static final long SCHEDULED_TASK_RATE = 100000;
//    private final IMessageService messageService;
//    private final SimpMessagingTemplate template;
//
//    @Scheduled(fixedRate = SCHEDULED_TASK_RATE)
//    public void getMessages() {
//        List<MessageDto> messages = messageService.getImagesFiles(messageService.getAll());
//        template.convertAndSend(SENDING_URL, messages);
//    }
//}
