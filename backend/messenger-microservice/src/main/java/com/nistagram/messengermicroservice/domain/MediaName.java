package com.nistagram.messengermicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MediaName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fileName;
    @Column
    private Boolean isImage = true;
}
