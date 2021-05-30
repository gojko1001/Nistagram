package com.nistagram.userMicroservice.domain;


import com.nistagram.userMicroservice.domain.enums.VerificationStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class VerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private VerificationStatus status;
    @Column
    private String approvedByUsername;
    @ManyToOne
    private UserCategory category;

}
