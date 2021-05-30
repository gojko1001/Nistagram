package com.nistagram.userMicroservice;

import com.nistagram.userMicroservice.domain.enums.Gender;
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
    private String pastUsername;
}
