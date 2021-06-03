package com.xws.nistagrammonolith.domain.media;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToMany
    private List<Favourite> favourites;
    @Column
    private String username;
}
