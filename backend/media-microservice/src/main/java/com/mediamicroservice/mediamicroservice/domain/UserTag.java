package com.mediamicroservice.mediamicroservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
}
