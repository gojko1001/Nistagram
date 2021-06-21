package com.nistagram.notificationmicroservice.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ImageBytesDto {
    private Long id;
    private String username;
    private Date timestamp;
    private List<Comment> comments;
    private List<Like> likes;
    private List<ImageByte> imageBytes;
    private Long mediaId;
}
