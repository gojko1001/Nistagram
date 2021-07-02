package com.nistagram.campaignmicroservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String url;
    @Column
    private Long mediaId;
    @ManyToOne
    private Campaign campaign;

}
