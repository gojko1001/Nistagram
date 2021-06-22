package com.nistagram.usermicroservice.user.controller.mapper;

import com.nistagram.usermicroservice.user.controller.dto.UserRelationDto;
import com.nistagram.usermicroservice.user.domain.UserRelation;

import java.util.ArrayList;
import java.util.List;

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
        relationDto.setTimestamp(relation.getTimestamp());
        return relationDto;
    }

    public static List<UserRelationDto> mapRelationsToRelationsDto(List<UserRelation> relations){
        List<UserRelationDto> relationDtos = new ArrayList<>();
        for(UserRelation r : relations)
            relationDtos.add(mapRelationToRelationDto(r));
        return relationDtos;
    }
}
