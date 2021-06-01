package com.nistagram.authenticationmicroservice.dto;

import lombok.Data;

@Data
public class LoginGoogleDto {
    private String email;
    private String name;
}
