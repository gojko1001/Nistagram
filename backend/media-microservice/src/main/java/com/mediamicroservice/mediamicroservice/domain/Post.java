package com.mediamicroservice.mediamicroservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Media media;
    @ManyToMany
    private List<Like> likes;
    @ManyToMany
    private List<Comment> comments;

}
