package com.xws.nistagrammonolith.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Image {
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
    @OneToMany
    private List<Tag> tags;
    @OneToMany
    private List<Comment> comments;        //TODO: fale like, dislike, favorites, album

    public Image(){}

    public Image(Long id, String fileName, String username, String description, Location location, List<Tag> tags, List<Comment> comments) {
        this.id = id;
        this.fileName = fileName;
        this.username = username;
        this.description = description;
        this.location = location;
        this.tags = tags;
        this.comments = comments;
    }
}
