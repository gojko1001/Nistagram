package com.nistagram.messengermicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String sender;
    private String receiver;
}
