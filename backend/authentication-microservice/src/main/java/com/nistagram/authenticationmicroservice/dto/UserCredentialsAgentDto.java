package com.nistagram.authenticationmicroservice.dto;

import lombok.Data;

@Data
public class UserCredentialsAgentDto {
    private String username;
    private String email;
    private String password;
    private String webSite;
    private String fullName;
    private String phone;
}
