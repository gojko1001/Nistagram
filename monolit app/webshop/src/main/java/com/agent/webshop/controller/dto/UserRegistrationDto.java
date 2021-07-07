package com.agent.webshop.controller.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private String webSite;
    private String role;
}
