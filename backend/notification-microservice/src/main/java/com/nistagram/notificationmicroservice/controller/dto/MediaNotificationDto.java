package com.nistagram.notificationmicroservice.controller.dto;

import com.nistagram.notificationmicroservice.domain.ImageByte;
import lombok.Data;

import java.util.Date;

@Data
public class MediaNotificationDto {
    private Long id;
    private String message;
    private String notifyUsername;
    private String actionUsername;
    private Date timestamp;
    private ImageByte imageByte;
}
