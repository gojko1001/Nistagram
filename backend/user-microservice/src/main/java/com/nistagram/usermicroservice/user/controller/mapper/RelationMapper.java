package com.nistagram.usermicroservice.user.controller.mapper;

import com.nistagram.usermicroservice.user.controller.dto.UserRelationDto;
import com.nistagram.usermicroservice.user.domain.UserRelation;

public class RelationMapper {

    public static UserRelationDto mapRelationToRelationDto(UserRelation relation){
        UserRelationDto relationDto = new UserRelationDto();
        relationDto.setUsername(relation.getUser().getUsername());
        relationDto.setRelatedUsername(relation.getRelatedUser().getUsername());
        relationDto.setStatus(relation.getRelationStatus());
        relationDto.setMutePost(relation.getMutePost());
        relationDto.setMuteStory(relation.getMuteStory());
        relationDto.setNotifyPost(relation.getNotifyPost());
        relationDto.setNotifyStory(relation.getNotifyStory());
        return relationDto;
    }
}
