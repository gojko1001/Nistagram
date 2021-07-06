package com.nistagram.campaignmicroservice.controller.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class MediaName {
    private String fileName;
    private boolean isImage = true;
}
