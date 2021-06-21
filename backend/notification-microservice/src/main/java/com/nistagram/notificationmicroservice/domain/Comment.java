package com.nistagram.notificationmicroservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class Comment {
    private Long id;
    private String text;
    private String username;
    private Date timestamp;
}
