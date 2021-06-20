package com.nistagram.usermicroservice.service.interfaces;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.domain.UserRelation;
import com.nistagram.usermicroservice.domain.enums.RelationStatus;
import com.nistagram.usermicroservice.controller.dto.UserRelationDto;

import java.util.List;

public interface IUserRelationService {

    List<User> getUserFollowers(String username);

    List<User> getUserFollowings(String username);

    List<User> getEagerFollowings(String username, RelationStatus status);

    void followUser(String username, String relatedUsername);

    void acceptFollower(String username, String relatedUsername);

    void blockUser(String username, String relatedUsername);

    void updateUserRelation(String username, String relatedUsername, RelationStatus status);

    void removeUserRelation(String username, String relatedUsername);

    void setNotifications(UserRelationDto relationDto);

    UserRelation findRelation(String username, String relatedUsername);
}
