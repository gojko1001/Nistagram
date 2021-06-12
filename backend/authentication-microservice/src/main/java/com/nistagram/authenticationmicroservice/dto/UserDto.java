package com.nistagram.authenticationmicroservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String fullName;
    private String email;
    private String phone;
    private Date birthDate;
    private String username;
    private String webSite;
    private String bio;
}
