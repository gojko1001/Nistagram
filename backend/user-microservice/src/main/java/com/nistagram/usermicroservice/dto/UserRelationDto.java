package com.nistagram.usermicroservice.dto;

import com.nistagram.usermicroservice.domain.enums.RelationStatus;
import lombok.Data;

@Data
public class UserRelationDto {
    private String username;
    private String relatedUsername;
    private RelationStatus status;
    private Boolean enableNotifications;
}
