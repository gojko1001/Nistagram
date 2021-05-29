package com.nistagram.authenticationMicroservice.dto;

import lombok.Data;

@Data
public class LoginGoogleDto {
    private String email;
    private String name;
}
