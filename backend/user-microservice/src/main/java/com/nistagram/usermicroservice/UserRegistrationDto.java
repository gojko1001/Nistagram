package com.nistagram.usermicroservice;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private String fullName;
}
