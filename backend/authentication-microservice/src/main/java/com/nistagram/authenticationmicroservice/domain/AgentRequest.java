package com.nistagram.authenticationmicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "requests")
@Data
public class AgentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column
    private String webSite;
    @Column
    private Boolean isRejected = false;
    @Column
    private Boolean isConfirmed = false;
}
