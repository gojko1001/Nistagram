package com.nistagram.notificationmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.management.Notification;
import java.util.Date;

@Controller
public class WebSocketController {
    private static final String SENDING_URL = "/topic/server-broadcaster";
    private static final String RECEIVING_URL = "/server-receiver";
    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    /*@SubscribeMapping(SENDING_URL)
    public Notification onSubscribe() {
        return new Notification("Connected", new Date());
    }*/

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        Date now = new Date();
        System.out.println("RADI LIII");
        
    }


}
