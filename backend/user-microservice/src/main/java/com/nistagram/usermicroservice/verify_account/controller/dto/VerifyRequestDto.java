package com.nistagram.usermicroservice.verify_account.controller.dto;

import com.nistagram.usermicroservice.verify_account.domain.UserCategory;
import com.nistagram.usermicroservice.verify_account.domain.VerificationStatus;
import lombok.Data;

@Data
public class VerifyRequestDto {
    private Long id;
    private VerificationStatus status;
    private String approvedByUsername;
    private UserCategory category;
    private String fileName;
    private String fullName;
    private byte[] imageBytes;
}
