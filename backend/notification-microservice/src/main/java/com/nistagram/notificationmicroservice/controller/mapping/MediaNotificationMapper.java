package com.nistagram.notificationmicroservice.controller.mapping;

import com.nistagram.notificationmicroservice.controller.dto.MediaNotificationDto;
import com.nistagram.notificationmicroservice.domain.MediaNotification;

public class MediaNotificationMapper {
    public static MediaNotificationDto mapMediaNotificationToMediaNotificationDto(MediaNotification mediaNotification) {
        MediaNotificationDto mediaNotificationDto = new MediaNotificationDto();
        mediaNotificationDto.setId(mediaNotification.getId());
        mediaNotificationDto.setMessage(mediaNotification.getMessage());
        mediaNotificationDto.setNotifyUsername(mediaNotification.getNotifyUsername());
        mediaNotificationDto.setActionUsername(mediaNotification.getMessage());
        mediaNotificationDto.setTimestamp(mediaNotification.getTimestamp());
        return mediaNotificationDto;
    }
}
