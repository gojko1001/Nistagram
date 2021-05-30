package com.nistagram.userMicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
