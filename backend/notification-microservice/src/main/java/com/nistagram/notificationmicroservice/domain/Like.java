package com.nistagram.notificationmicroservice.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Like {
    private Long id;
    private String username;
    private boolean liked = true;
    private Date timestamp;
    private Long postId;
}
