package com.nistagram.usermicroservice.service;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.dto.UserRelationDto;

import java.util.List;

public interface IUserRelationService {

    List<User> getUserFollowings(String username);

    List<User> getUserFollowers(String username);

    void followUser(UserRelationDto relationDto);

    void acceptFollower(UserRelationDto relationDto);

    void blockUser(UserRelationDto relationDto);

    void updateUserRelation(UserRelationDto relationDto);

    void removeUserRelation(UserRelationDto userRelationDto);
}
