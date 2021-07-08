package com.agent.webshop.controller.dto;

import lombok.Data;

@Data
public class AdDto {
    private String url;
    private String description;
    private String fileName;
    private Long mediaId;
}
