package com.nistagram.usermicroservice.user.service.interfaces;

import com.nistagram.usermicroservice.user.domain.User;
import com.nistagram.usermicroservice.user.domain.UserRelation;
import com.nistagram.usermicroservice.user.domain.enums.RelationStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;

@Service
public interface IUserRelationService {

    UserRelation findRelation(String username, String relatedUsername);

    List<UserRelation> findAllRequestsAndFollowings();

    List<User> getUserFollowers(String username);

    List<User> getUserFollowings(String username);

    List<User> getUserRequests(String username);

    List<User> getEagerFollowings(String username, RelationStatus status);

    void followUser(String username, String relatedUsername);

    void acceptFollower(String username, String relatedUsername);

    void blockUser(String username, String relatedUsername);

    void updateUserRelation(String username, String relatedUsername, RelationStatus status);

    void removeUserRelation(String username, String relatedUsername);

    void setRelationBoolean(String username, String relatedUsername, BiConsumer<UserRelation, Boolean> relationFunction, Boolean isMuted);
}
