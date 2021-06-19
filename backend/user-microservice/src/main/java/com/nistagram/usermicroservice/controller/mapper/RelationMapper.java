package com.nistagram.usermicroservice.controller.mapper;

import com.nistagram.usermicroservice.controller.dto.UserRelationDto;
import com.nistagram.usermicroservice.domain.UserRelation;

public class RelationMapper {

    public static UserRelationDto mapRelationToRelationDto(UserRelation relation){
        UserRelationDto relationDto = new UserRelationDto();
        relationDto.setUsername(relation.getUser().getUsername());
        relationDto.setRelatedUsername(relation.getRelatedUser().getUsername());
        relationDto.setStatus(relation.getRelationStatus());
        relationDto.setEnableNotifications(relation.getEnableNotification());
        return relationDto;
    }
}
