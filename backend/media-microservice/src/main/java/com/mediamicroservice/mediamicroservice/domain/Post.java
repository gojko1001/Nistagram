package com.mediamicroservice.mediamicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Media media;
}
