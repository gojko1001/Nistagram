package com.mediamicroservice.mediamicroservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "likes")
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private boolean liked = true;
    @Column
    private Date timestamp;
}
