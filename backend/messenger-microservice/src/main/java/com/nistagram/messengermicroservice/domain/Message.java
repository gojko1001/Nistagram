package com.nistagram.messengermicroservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @Column
    private String sender;
    @Column
    private String receiver;
    @Column
    private Date date;
}
