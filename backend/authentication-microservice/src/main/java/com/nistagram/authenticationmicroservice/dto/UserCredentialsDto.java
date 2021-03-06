package com.nistagram.authenticationmicroservice.dto;

import lombok.Data;

@Data
public class UserCredentialsDto {
    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private String fullName;

}
