package com.nistagram.messengermicroservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;
    @Column
    private Boolean hasMedia = false;
    @ManyToMany
    private List<MediaName> mediaName;
    @Column
    private Boolean viewed = false;
}
