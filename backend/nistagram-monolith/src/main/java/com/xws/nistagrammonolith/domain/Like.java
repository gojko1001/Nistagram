package com.xws.nistagrammonolith.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "likes")
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
}
