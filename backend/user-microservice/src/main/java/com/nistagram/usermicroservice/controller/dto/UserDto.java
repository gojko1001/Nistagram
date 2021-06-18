package com.nistagram.usermicroservice.controller.dto;

import com.nistagram.usermicroservice.domain.enums.Gender;
import com.nistagram.usermicroservice.verify_account.domain.VerificationStatus;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String webSite;
    private String bio;
    private VerificationStatus status;
    private Date birthDate;
    private Gender userGender;
    private boolean publicProfile;
    private boolean publicDM;
    private boolean taggable;
}
