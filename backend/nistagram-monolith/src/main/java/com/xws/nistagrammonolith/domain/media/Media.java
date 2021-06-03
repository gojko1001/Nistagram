package com.xws.nistagrammonolith.domain.media;

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
    @Column(nullable = false, unique = true, length = 64)
    private String fileName;
    @Column(nullable = false)
    private String username;
    @Column
    private String description;
    @Column
    private boolean isImage = true;
    @Column
    private Date timestamp;
    @ManyToMany
    private List<UserTag> userTags;
    @ManyToMany
    private List<Hashtag> hashtags;
    @OneToOne
    private Location location;
    @OneToOne
    private Album album;
}
