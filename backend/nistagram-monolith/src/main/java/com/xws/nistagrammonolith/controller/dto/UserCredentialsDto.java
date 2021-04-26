package com.xws.nistagrammonolith.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentialsDto {
    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private String fullName;

    UserCredentialsDto(){}

    public UserCredentialsDto(String username, String email, String password, String repeatPassword, String fullName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.fullName = fullName;
    }
}
