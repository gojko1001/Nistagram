package com.nistagram.authenticationmicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "blacklist")
@Entity
@Data
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String password;
}
