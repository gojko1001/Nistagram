package com.nistagram.authenticationmicroservice.dto;

import lombok.Data;

@Data
public class AgentRequestDto {
    private Long id;
    private String username;
    private String email;
    private String webSite;
}
