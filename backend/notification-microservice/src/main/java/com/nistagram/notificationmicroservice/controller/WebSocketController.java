package com.nistagram.notificationmicroservice.controller;

import com.nistagram.notificationmicroservice.connection.MediaConnection;
import com.nistagram.notificationmicroservice.controller.dto.NotificationDto;
import com.nistagram.notificationmicroservice.domain.*;
import com.nistagram.notificationmicroservice.service.interfaces.IMediaNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.util.Date;
import java.util.List;

@Controller
public class WebSocketController {
    private static final String SENDING_URL = "/topic/server-broadcaster";
    private static final String RECEIVING_URL = "/server-receiver";
    private final SimpMessagingTemplate template;

    @Autowired
    private IMediaNotificationService mediaNotificationService;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    /*@SubscribeMapping(SENDING_URL)
    public NotificationDto onSubscribe() {
        return new NotificationDto("Connected", new Date(), "");
    }*/

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        Date now = new Date();
        List<ImageBytesDto> imageBytesDtos = mediaNotificationService.getAllPosts();
        for(ImageBytesDto imageBytesDto: imageBytesDtos){
            checkComments(imageBytesDto, now);
            checkLikes(imageBytesDto, now);
        }
    }



    // business logic
    public void checkComments(ImageBytesDto imageBytesDto, Date now){
        if(imageBytesDto.getComments() != null){
            for(Comment comment: imageBytesDto.getComments()){
                long commentTimestamp = comment.getTimestamp().getTime();
                if(commentTimestamp <= now.getTime() && commentTimestamp > now.getTime()-10000){
                    MediaNotification mediaNotification = new MediaNotification();
                    mediaNotification.setMediaId(imageBytesDto.getMediaId());
                    mediaNotification.setNotifyUsername(imageBytesDto.getUsername());
                    mediaNotification.setActionUsername(comment.getUsername());
                    mediaNotification.setTimestamp(now);
                    mediaNotification.setMessage("New comment on post by " + comment.getUsername());
                    mediaNotificationService.save(mediaNotification);
                    template.convertAndSend(SENDING_URL, new NotificationDto(mediaNotification.getMessage(), mediaNotification.getTimestamp(), mediaNotification.getNotifyUsername()));
                    System.out.println("New comment on post " + imageBytesDto.getId() + " by " + comment.getUsername());
                }
            }
        }
    }

    public void checkLikes(ImageBytesDto imageBytesDto, Date now){
        if(imageBytesDto.getLikes() != null){
            for(Like like: imageBytesDto.getLikes()){
                long likeTimestamp = like.getTimestamp().getTime();
                if(likeTimestamp <= now.getTime() && likeTimestamp > now.getTime()-10000){
                    MediaNotification mediaNotification = new MediaNotification();
                    mediaNotification.setMediaId(imageBytesDto.getMediaId());
                    mediaNotification.setNotifyUsername(imageBytesDto.getUsername());
                    mediaNotification.setActionUsername(like.getUsername());
                    mediaNotification.setTimestamp(now);
                    mediaNotification.setMessage("New reaction on post by " + like.getUsername());
                    mediaNotificationService.save(mediaNotification);
                    template.convertAndSend(SENDING_URL, new NotificationDto(mediaNotification.getMessage(), mediaNotification.getTimestamp(), mediaNotification.getNotifyUsername()));
                    System.out.println("New reaction on post " + imageBytesDto.getId() + " by " + like.getUsername());
                }
            }
        }
    }

}
