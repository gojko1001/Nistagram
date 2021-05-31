package com.nistagram.usermicroservice.domain;

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
