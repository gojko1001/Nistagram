package com.agent.webshop.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column
    private String password;
}
