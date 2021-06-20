package com.nistagram.notificationmicroservice.controller;

import com.nistagram.notificationmicroservice.connection.MediaConnection;
import com.nistagram.notificationmicroservice.domain.Comment;
import com.nistagram.notificationmicroservice.domain.ImageByte;
import com.nistagram.notificationmicroservice.domain.ImageBytesDto;
import com.nistagram.notificationmicroservice.domain.MediaNotification;
import com.nistagram.notificationmicroservice.service.interfaces.IMediaNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

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
    public Notification onSubscribe() {
        return new Notification("Connected", new Date());
    }*/

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        Date now = new Date();
        List<ImageBytesDto> imageBytesDtos = mediaNotificationService.getAllPosts();
        for(ImageBytesDto imageBytesDto: imageBytesDtos){
            // check if there is a new comment on post
            if(imageBytesDto.getComments() != null){
                for(Comment comment: imageBytesDto.getComments()){
                    long commentTimestamp = comment.getTimestamp().getTime();
                    if(commentTimestamp <= now.getTime() && commentTimestamp > now.getTime()-10000){
                        //template.convertAndSend(SENDING_URL, new Notification("New job in " + conn.getName(), now));
                        MediaNotification mediaNotification = new MediaNotification();
                        mediaNotification.setMediaId(imageBytesDto.getMediaId());
                        mediaNotification.setNotifyUsername(imageBytesDto.getUsername());
                        mediaNotification.setActionUsername(comment.getUsername());
                        mediaNotification.setTimestamp(now);
                        mediaNotification.setMessage("New comment on post by " + comment.getUsername());
                        mediaNotificationService.save(mediaNotification);
                        System.out.println("New comment on post " + imageBytesDto.getId() + " by " + comment.getUsername());
                    }
                }
            }

        }
    }


}
