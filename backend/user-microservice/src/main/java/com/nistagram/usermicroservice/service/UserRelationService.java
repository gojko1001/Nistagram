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

    public void makeUserRelation(UserRelationDto relationDto){
        if(relationDto.getStatus() != RelationStatus.BLOCKED && relationDto.getStatus() != RelationStatus.PENDING)
            throw new InvalidActionException("Invalid action");
        User user = userService.findUserByUsername(relationDto.getUsername());
        if(findRelation(relationDto.getUsername(), relationDto.getRelatedUsername()) != null && relationDto.getStatus() == RelationStatus.PENDING)
            throw new InvalidActionException("Invalid action");
        User relatedUser = userService.findUserByUsername(relationDto.getRelatedUsername());
        UserRelation userRelation = new UserRelation(user, relatedUser, relationDto.getStatus(), false);
        user.getUserRelations().add(userRelation);
        userService.save(user);
    };

    public void AcceptFollower(UserRelationDto relationDto){
        User user = userService.findUserByUsername(relationDto.getRelatedUsername());
        UserRelation relation = findRelation(relationDto.getRelatedUsername(), relationDto.getUsername());
        if(relation == null)
            throw new InvalidActionException("Following request doesn't exist!");
        relation.setRelationStatus(RelationStatus.FOLLOWING);
        userService.save(user);
    }


    public void updateUserRelation(UserRelationDto relationDto){
        User user = userService.findUserByUsername(relationDto.getUsername());
        UserRelation userRelation = findRelation(relationDto.getUsername(), relationDto.getRelatedUsername());
        if(userRelation == null)
            throw new InvalidActionException("Invalid action");
        switch (relationDto.getStatus()){
            case BLOCKED:
                userRelation.setRelationStatus(RelationStatus.BLOCKED);
                break;
            case FOLLOWING:
            case MUTED:
            case CLOSE_FRIEND:
                userRelation.setRelationStatus(relationDto.getStatus());
                break;
            default:
                throw new InvalidActionException("Invalid action!");
        }
        // TODO: Zameniti novu relaciju?
        userService.save(user);
    }

    public void removeUserRelation(UserRelationDto userRelationDto){
        User user = userService.findUserByUsername(userRelationDto.getUsername());
        UserRelation toDelete = findRelation(userRelationDto.getUsername(), userRelationDto.getRelatedUsername());
        user.getUserRelations().remove(toDelete);
        userService.save(user);
    }

    private UserRelation findRelation(String username, String relatedUsername){
        for(UserRelation relatedUser : userService.findUserByUsername(username).getUserRelations())
            if(relatedUser.getRelatedUser().getUsername().equals(relatedUsername))
                return relatedUser;
        return null;
    }
}
