package com.mediamicroservice.mediamicroservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<MediaName> mediaName;
    @Column(nullable = false)
    private String username;
    @Column
    private String description;
    @Column
    private Date timestamp;
    @ManyToMany
    private List<Hashtag> hashtags;
    @OneToOne
    private Location location;
    @ManyToMany
    private List<UserTag> userTags;

}
