package com.nistagram.usermicroservice.service.interfaces;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.domain.enums.RelationStatus;
import com.nistagram.usermicroservice.controller.dto.UserRelationDto;

import java.util.List;

public interface IUserRelationService {

    List<User> getUserFollowers(String username);

    List<User> getUserFollowings(String username);

    List<User> getEagerFollowings(String username, RelationStatus status);

    void followUser(UserRelationDto relationDto);

    void acceptFollower(UserRelationDto relationDto);

    void blockUser(UserRelationDto relationDto);

    void updateUserRelation(UserRelationDto relationDto);

    void removeUserRelation(UserRelationDto userRelationDto);

    void setNotifications(UserRelationDto relationDto);
}
