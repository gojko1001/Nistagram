package com.nistagram.usermicroservice.controller.dto;

import com.nistagram.usermicroservice.domain.enums.Gender;
import com.nistagram.usermicroservice.verify_account.domain.VerificationStatus;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String fullName;
    private String email;
    private String phone;
    private Gender userGender;
    private Date birthDate;
    private String username;
    private String webSite;
    private String bio;
    private String status;
}
