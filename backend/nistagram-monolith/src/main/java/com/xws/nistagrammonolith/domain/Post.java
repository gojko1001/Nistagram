package com.xws.nistagrammonolith.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 64)
    private String fileName;
    @Column(nullable = false)
    private String username;
    @Column
    private String description;
    @OneToOne
    private Location location;
    @ManyToMany
    private List<Tag> tags;
    @ManyToMany
    private List<Comment> comments;        //TODO: fale like, dislike, favorites, album
    @Column
    private boolean isImage = true;

}
