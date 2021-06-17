package com.nistagram.usermicroservice.verify_account.controller.dto;

import com.nistagram.usermicroservice.verify_account.domain.UserCategory;
import lombok.Data;

@Data
public class CreateVerifyRequestDto {
    private String fullName;
    private UserCategory category;
    private String fileName;
}
