package com.nistagram.usermicroservice.dto;

import com.nistagram.usermicroservice.domain.enums.Gender;
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
    private Date birthDate;
    private Gender userGender;
    private boolean publicProfile;
    private boolean publicDM;
    private boolean taggable;
}
