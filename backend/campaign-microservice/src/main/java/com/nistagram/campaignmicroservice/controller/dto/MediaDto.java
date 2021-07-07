package com.nistagram.campaignmicroservice.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class MediaDto {
    private String username;
    private List<String> fileNames;
    private String description;
    private MediaName mediaName;
}
