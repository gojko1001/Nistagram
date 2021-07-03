package com.nistagram.usermicroservice.user.controller.dto;

import lombok.Data;

@Data
public class AgentDto {
    private String username;
    private String email;
    private String password;
    private String webSite;
    private String fullName;
    private String phone;
}
