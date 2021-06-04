package com.nistagram.usermicroservice.service;

import com.nistagram.usermicroservice.dto.UserRelationDto;

public interface IUserRelationService {
    void followUser(UserRelationDto relationDto);

    void acceptFollower(UserRelationDto relationDto);

    void blockUser(UserRelationDto relationDto);

    void updateUserRelation(UserRelationDto relationDto);

    void removeUserRelation(UserRelationDto userRelationDto);
}
