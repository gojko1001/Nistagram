package com.nistagram.usermicroservice.service;

import com.nistagram.usermicroservice.dto.UserRelationDto;

public interface IUserRelationService {
    void FollowUser(UserRelationDto relationDto);

    void AcceptFollower(UserRelationDto relationDto);

    void blockUser(UserRelationDto relationDto);

    void updateUserRelation(UserRelationDto relationDto);

    void removeUserRelation(UserRelationDto userRelationDto);
}
