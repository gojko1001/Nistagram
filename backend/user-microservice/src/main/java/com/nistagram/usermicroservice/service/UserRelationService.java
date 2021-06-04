package com.nistagram.usermicroservice.service;

import com.nistagram.usermicroservice.domain.UserRelation;
import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.domain.enums.RelationStatus;
import com.nistagram.usermicroservice.dto.UserRelationDto;
import com.nistagram.usermicroservice.exception.InvalidActionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService implements IUserRelationService {

    @Autowired
    private IUserService userService;


    public void followUser(UserRelationDto relationDto){
        if(findRelation(relationDto.getUsername(), relationDto.getRelatedUsername()) != null || isBlocked(relationDto.getUsername(), relationDto.getRelatedUsername()))
            throw new InvalidActionException("You are already following or blocked eager user");
        User user = userService.findUserByUsername(relationDto.getUsername());
        User relatedUser = userService.findUserByUsername(relationDto.getRelatedUsername());
        RelationStatus status = RelationStatus.PENDING;
        if(relatedUser.isPublicProfile())
            status = RelationStatus.FOLLOWING;
        UserRelation relation = new UserRelation(user, relatedUser, status, false);
        user.getUserRelations().add(relation);
        userService.save(user);
    }

    public void acceptFollower(UserRelationDto relationDto){
        User user = userService.findUserByUsername(relationDto.getRelatedUsername());
        UserRelation relation = findRelation(relationDto.getRelatedUsername(), relationDto.getUsername());
        if(relation == null)
            throw new InvalidActionException("Following request doesn't exist!");
        relation.setRelationStatus(RelationStatus.FOLLOWING);
        userService.save(user);
    }

    public void updateUserRelation(UserRelationDto relationDto){
        if(relationDto.getStatus() != RelationStatus.FOLLOWING && relationDto.getStatus() != RelationStatus.MUTED &&
            relationDto.getStatus() != RelationStatus.CLOSE_FRIEND || isBlocked(relationDto.getUsername(), relationDto.getRelatedUsername()))
            throw new InvalidActionException("Invalid action");
        User user = userService.findUserByUsername(relationDto.getUsername());
        UserRelation userRelation = findRelation(relationDto.getUsername(), relationDto.getRelatedUsername());
        if(userRelation == null)
            throw new InvalidActionException("Invalid action");
        userRelation.setRelationStatus(relationDto.getStatus());
        // TODO: Zameniti novu relaciju?
        userService.save(user);
    }

    public void blockUser(UserRelationDto relationDto){
        if(isBlocked(relationDto.getUsername(), relationDto.getRelatedUsername()))
            throw new InvalidActionException("User unknown");
        User user = userService.findUserByUsername(relationDto.getUsername());
        User relatedUser = userService.findUserByUsername(relationDto.getRelatedUsername());
        UserRelation relation = findRelation(relationDto.getUsername(), relationDto.getRelatedUsername());
        if(relation != null){
            relation.setRelationStatus(RelationStatus.BLOCKED);
        }else{
            relation = new UserRelation(user, relatedUser, RelationStatus.BLOCKED, false);
            user.getUserRelations().add(relation);
        }
        userService.save(user);
        relationDto.setUsername(relatedUser.getUsername());
        relationDto.setRelatedUsername(user.getUsername());
        removeUserRelation(relationDto);
    }

    public void removeUserRelation(UserRelationDto relationDto){
        User user = userService.findUserByUsername(relationDto.getUsername());
        UserRelation toDelete = findRelation(relationDto.getUsername(), relationDto.getRelatedUsername());
        user.getUserRelations().remove(toDelete);
        userService.save(user);
    }

    private UserRelation findRelation(String username, String relatedUsername){
        for(UserRelation relatedUser : userService.findUserByUsername(username).getUserRelations())
            if(relatedUser.getRelatedUser().getUsername().equals(relatedUsername))
                return relatedUser;
        return null;
    }

    private boolean isBlocked(String username, String relatedUsername){
        UserRelation relation = findRelation(username, relatedUsername);
        if(relation == null)
            return false;
        return relation.getRelationStatus() == RelationStatus.BLOCKED;
    }
}
