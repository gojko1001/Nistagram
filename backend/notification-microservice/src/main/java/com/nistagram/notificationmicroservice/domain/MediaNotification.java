package com.nistagram.notificationmicroservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class MediaNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String message;
    @Column
    private String notifyUsername;
    @Column
    private String actionUsername;
    @Column
    private Date timestamp;
    @Column
    private Long mediaId;

}
