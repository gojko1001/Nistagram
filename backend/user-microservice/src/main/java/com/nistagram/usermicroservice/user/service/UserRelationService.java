package com.nistagram.usermicroservice.user.service;

import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.user.domain.User;
import com.nistagram.usermicroservice.user.domain.UserRelation;
import com.nistagram.usermicroservice.user.domain.UserRelationKey;
import com.nistagram.usermicroservice.user.domain.enums.RelationStatus;
import com.nistagram.usermicroservice.exception.InvalidActionException;
import com.nistagram.usermicroservice.user.repository.IUserRelationRepository;
import com.nistagram.usermicroservice.user.service.interfaces.IUserRelationService;
import com.nistagram.usermicroservice.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;

@Service
public class UserRelationService implements IUserRelationService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRelationRepository relationRepository;

    private User findUserByUsername(String username) {
        Logger.infoDb("Read user with username: " + username + ".");
        return userService.findUserByUsername(username);
    }

    public List<UserRelation> findAllRequestsAndFollowings(){
        List<UserRelation> relations = new ArrayList<>();
        for(UserRelation relation : relationRepository.findAll())
            if(relation.getRelationStatus() == RelationStatus.PENDING || relation.getRelationStatus() == RelationStatus.FOLLOWING)
                relations.add(relation);
        return relations;
    }

    public List<User> getUserFollowers(String username) {
        User user = findUserByUsername(username);
        List<User> followers = new ArrayList<>();
        for (UserRelation u : user.getInvertedRelations()) {
            if (isRelationFollowingGroup(u) && !isBlocked(username, u.getUser().getUsername()))
                followers.add(u.getUser());
        }
        return followers;
    }

    public List<User> getUserFollowings(String username) {
        User user = findUserByUsername(username);
        List<User> following = new ArrayList<>();
        for (UserRelation u : user.getUserRelations()) {
            if (isRelationFollowingGroup(u) && !isBlocked(u.getRelatedUser().getUsername(), username))
                following.add(u.getRelatedUser());
        }
        return following;
    }

    public List<User> getUserRequests(String username){
        User user = findUserByUsername(username);
        List<User> followers = new ArrayList<>();
        for (UserRelation u : user.getInvertedRelations()) {
            if (u.getRelationStatus() == RelationStatus.PENDING && !isBlocked(username, u.getUser().getUsername()))
                followers.add(u.getUser());
        }
        return followers;
    }

    public List<User> getEagerFollowings(String username, RelationStatus status) {
        User user = findUserByUsername(username);
        List<User> following = new ArrayList<>();
        for (UserRelation u : user.getUserRelations()) {
            if (u.getRelationStatus() == status && !isBlocked(u.getRelatedUser().getUsername(), username))
                following.add(u.getRelatedUser());
        }
        return following;
    }

    public void followUser(String username, String relatedUsername) {
        if (findRelation(username, relatedUsername) != null ||
                isBlocked(username, relatedUsername) ||
                isBlocked(relatedUsername, username))
            throw new InvalidActionException("You are already following or blocked eager user");
        User user = findUserByUsername(username);
        User relatedUser = findUserByUsername(relatedUsername);
        RelationStatus status = RelationStatus.PENDING;
        if (relatedUser.isPublicProfile())
            status = RelationStatus.FOLLOWING;
        UserRelation relation = new UserRelation(user, relatedUser, status);
        relation.setId(new UserRelationKey(user.getId(), relatedUser.getId()));
        relation.setTimestamp(new Date());
        user.getUserRelations().add(relation);
        userService.save(user);
    }

    public void acceptFollower(String username, String relatedUsername) {
        User user = findUserByUsername(relatedUsername);
        UserRelation relation = findRelation(relatedUsername, username);
        if (relation == null)
            throw new InvalidActionException("Following request doesn't exist!");
        if (relation.getRelationStatus() != RelationStatus.PENDING)
            throw new InvalidActionException("Following request doesn't exist!");
        relation.setRelationStatus(RelationStatus.FOLLOWING);
        relation.setTimestamp(new Date());
        userService.save(user);
    }

    public void updateUserRelation(String username, String relatedUsername, RelationStatus status) {
        if (status != RelationStatus.FOLLOWING && status != RelationStatus.CLOSE_FRIEND ||
                isBlocked(username, relatedUsername) ||
                isBlocked(relatedUsername, username))
            throw new InvalidActionException("Invalid action");
        User user = findUserByUsername(username);
        UserRelation userRelation = findRelation(username, relatedUsername);
        if (userRelation == null)
            throw new InvalidActionException("Invalid action");
        userRelation.setRelationStatus(status);
        userService.save(user);
    }

    public void blockUser(String username, String relatedUsername) {
        if (isBlocked(username, relatedUsername))
            throw new InvalidActionException("User not found!");
        User user = findUserByUsername(username);
        User relatedUser = findUserByUsername(relatedUsername);
        UserRelation relation = findRelation(username, relatedUsername);
        if (relation != null) {
            relation.setRelationStatus(RelationStatus.BLOCKED);

        } else {
            relation = new UserRelation(user, relatedUser, RelationStatus.BLOCKED);
            relation.setId(new UserRelationKey(user.getId(), relatedUser.getId()));
            user.getUserRelations().add(relation);
        }
        userService.save(user);
        removeUserRelation(relatedUsername, username);
    }

    public void removeUserRelation(String username, String relatedUsername) {
        User user = findUserByUsername(username);
        UserRelation toDelete = findRelation(username, relatedUsername);
        user.getUserRelations().remove(toDelete);
        userService.save(user);
    }

    public void setRelationBoolean(String username, String relatedUsername, BiConsumer<UserRelation, Boolean> relationFunction, Boolean isMuted){
        if (isBlocked(username, relatedUsername))
            throw new InvalidActionException("User is blocked!");
        User user = findUserByUsername(username);
        UserRelation userRelation = findRelation(username, relatedUsername);
        if (userRelation == null)
            throw new InvalidActionException("You are not following user: " + relatedUsername);
        relationFunction.accept(userRelation, isMuted);
        userService.save(user);
    }

    @Override
    public boolean isFollow(String followerUsername, String followingUsername) {
        List<User> users = getUserFollowings(followerUsername);
        for(User user: users){
            if(user.getUsername().equals(followingUsername))
                return true;
        }
        return false;
    }

    public UserRelation findRelation(String username, String relatedUsername) {
        for (UserRelation relatedUser : findUserByUsername(username).getUserRelations())
            if (relatedUser.getRelatedUser().getUsername().equals(relatedUsername))
                return relatedUser;
        return null;
    }

    private boolean isRelationFollowingGroup(UserRelation relation) {
        return relation.getRelationStatus() == RelationStatus.FOLLOWING ||
                relation.getRelationStatus() == RelationStatus.CLOSE_FRIEND;
    }

    private boolean isBlocked(String username, String relatedUsername) {
        UserRelation relation = findRelation(username, relatedUsername);
        if (relation == null)
            return false;
        return relation.getRelationStatus() == RelationStatus.BLOCKED;
    }
}
