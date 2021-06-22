package com.nistagram.notificationmicroservice.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserRelationDto {
    private String username;
    private String relatedUsername;
    private RelationStatus status;
    private Boolean mutePost;
    private Boolean muteStory;
    private Boolean notifyPost;
    private Boolean notifyStory;
    private Date timestamp;
}
