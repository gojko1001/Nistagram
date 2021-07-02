package com.nistagram.campaignmicroservice.domain;

import com.nistagram.campaignmicroservice.domain.enums.RequestStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PromoteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private RequestStatus status;
    @Column
    private String username;
    @ManyToOne
    private Campaign campaign;
}
