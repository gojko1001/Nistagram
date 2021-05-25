package com.xws.nistagrammonolith.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column
    private String messageText;
    @Column
    private String fromLogin;
    @Column
    private Date date;
}
