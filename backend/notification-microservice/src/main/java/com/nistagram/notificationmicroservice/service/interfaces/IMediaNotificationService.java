package com.nistagram.notificationmicroservice.service.interfaces;

import com.nistagram.notificationmicroservice.controller.dto.MediaNotificationDto;
import com.nistagram.notificationmicroservice.domain.ImageBytesDto;
import com.nistagram.notificationmicroservice.domain.MediaNotification;

import java.util.List;

public interface IMediaNotificationService {
    MediaNotification save(MediaNotification mediaNotification);
    List<ImageBytesDto> getAllPosts();
    List<MediaNotificationDto> getMediaNotificationsByUsername(String username);
}
