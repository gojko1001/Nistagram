package com.mediamicroservice.mediamicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @OneToOne
    private Post post;
}
