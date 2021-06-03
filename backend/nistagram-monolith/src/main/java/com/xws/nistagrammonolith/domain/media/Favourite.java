package com.xws.nistagrammonolith.domain.media;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Post post;
}
