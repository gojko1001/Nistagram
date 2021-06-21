package com.nistagram.notificationmicroservice.controller;

import com.nistagram.notificationmicroservice.controller.dto.MediaNotificationDto;
import com.nistagram.notificationmicroservice.domain.MediaNotification;
import com.nistagram.notificationmicroservice.service.interfaces.IMediaNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
@CrossOrigin(origins = "http://localhost:3000")
public class MediaNotificationController {
    @Autowired
    private IMediaNotificationService mediaNotificationService;

    @GetMapping("/{username}")
    public List<MediaNotificationDto> getMediaNotificationsByUsername(@PathVariable("username") String username) {
        return mediaNotificationService.getMediaNotificationsByUsername(username);
    }
}
