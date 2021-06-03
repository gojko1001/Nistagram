package com.mediamicroservice.mediamicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Media media;
    @Column
    private boolean highlighted = false;
    @Column
    private boolean forCloseFriends = false;
}
