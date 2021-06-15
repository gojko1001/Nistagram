package com.nistagram.usermicroservice.verify_account.domain;


import com.nistagram.usermicroservice.domain.enums.VerificationStatus;
import com.nistagram.usermicroservice.verify_account.domain.UserCategory;
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
    @Column
    private String filePath;

}
