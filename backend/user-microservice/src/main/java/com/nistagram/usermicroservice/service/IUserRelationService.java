package com.nistagram.usermicroservice.service;

import com.nistagram.usermicroservice.dto.UserRelationDto;

public interface IUserRelationService {
    void makeUserRelation(UserRelationDto userRelationDto);

    void AcceptFollower(UserRelationDto relationDto);

    void updateUserRelation(UserRelationDto relationDto);

    void removeUserRelation(UserRelationDto userRelationDto);
}
