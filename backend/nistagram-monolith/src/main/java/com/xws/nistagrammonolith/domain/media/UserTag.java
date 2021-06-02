package com.xws.nistagrammonolith.domain.media;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
}
