package com.mediamicroservice.mediamicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class InappropriateContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String requestedBy;
    @Column
    private RequestStatus requestStatus;
    @Column
    private String responedBy;
    @OneToOne
    private Media media;
}
