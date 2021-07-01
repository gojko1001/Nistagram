package com.agent.webshop.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "app_user")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column
    private String fullName;
    @Column(nullable = false)
    private String email;
    @Column
    private String phone;
    @Column
    private String webSite;
    @Column
    private String bio;
}
