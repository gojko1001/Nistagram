package com.nistagram.notificationmicroservice.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationDto {
    private String message;
    private Date timestamp;
    private String receiver;

    public NotificationDto(String message, Date timestamp, String receiver){
        super();
        this.message = message;
        this.timestamp = timestamp;
        this.receiver = receiver;
    }

    public NotificationDto(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
