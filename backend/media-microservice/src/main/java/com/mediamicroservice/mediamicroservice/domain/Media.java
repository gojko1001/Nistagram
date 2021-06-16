package com.mediamicroservice.mediamicroservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+02:00")
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
