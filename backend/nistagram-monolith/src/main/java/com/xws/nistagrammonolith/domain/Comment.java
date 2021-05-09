package com.xws.nistagrammonolith.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private String username;

    public Comment(){}

    public Comment(Long id, String text, String username) {
        this.id = id;
        this.text = text;
        this.username = username;
    }
}
