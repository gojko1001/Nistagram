package com.xws.nistagrammonolith.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "likes")
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column
    private boolean liked = true;
    @Column
    @Temporal(TemporalType.DATE)
    private Date timestamp;
    @OneToOne
    private Post post;
}
