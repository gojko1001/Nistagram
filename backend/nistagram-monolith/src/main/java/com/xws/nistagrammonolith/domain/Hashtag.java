package com.xws.nistagrammonolith.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

}
