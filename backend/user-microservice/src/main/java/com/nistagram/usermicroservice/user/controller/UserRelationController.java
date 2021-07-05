package com.nistagram.usermicroservice.user.controller;

import com.nistagram.usermicroservice.user.controller.dto.UserDto;
import com.nistagram.usermicroservice.user.controller.dto.UserRelationDto;
import com.nistagram.usermicroservice.exception.NotFoundException;
import com.nistagram.usermicroservice.exception.UnauthorizedException;
import com.nistagram.usermicroservice.user.controller.mapper.RelationMapper;
import com.nistagram.usermicroservice.user.controller.mapper.UserMapper;
import com.nistagram.usermicroservice.user.domain.UserRelation;
import com.nistagram.usermicroservice.user.domain.enums.RelationStatus;
import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.user.service.interfaces.IUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user_relation")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRelationController {
    @Autowired
    private IUserRelationService relationService;
//    @Autowired
//    private JwtUtil jwtUtil;

    @GetMapping("/{username}/{relatedUsername}")
    public UserRelationDto getUserRelation(@PathVariable String username, @PathVariable String relatedUsername) {
        UserRelation relation = relationService.findRelation(username, relatedUsername);
        if(relation == null)
            throw new NotFoundException("No relation found!");
        return RelationMapper.mapRelationToRelationDto(relation);
    }

    @GetMapping
    public List<UserRelationDto> getAllRequestsAndFollowings() {
        return RelationMapper.mapRelationsToRelationsDto(relationService.findAllRequestsAndFollowings());
    }

    @GetMapping("/followers/{username}")
    public List<UserDto> getFollowers(@PathVariable String username){
        Logger.info("Get followers.", username);
        return UserMapper.mapUserListToUserDtoList(relationService.getUserFollowers(username));
    }

    @GetMapping("/followings/{username}")
    public List<UserDto> getFollowings(@PathVariable String username){
        Logger.info("Get followings.", username);
        return UserMapper.mapUserListToUserDtoList(relationService.getUserFollowings(username));
    }

    @GetMapping("/requests")
    public List<UserDto> getFollowRequests(@RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Get pending users.", username);
        return UserMapper.mapUserListToUserDtoList(relationService.getUserRequests(username));
    }

    @GetMapping("/blocked/{username}")
    public List<UserDto> getBlockedUsers(@PathVariable String username){
        Logger.info("Get blocked users.", username);
        return UserMapper.mapUserListToUserDtoList(relationService.getEagerFollowings(username, RelationStatus.BLOCKED));
    }

    @GetMapping("/close_friends/{username}")
    public List<UserDto> getCloseFriends(@PathVariable String username){
        Logger.info("Get close friends.", username);
        return UserMapper.mapUserListToUserDtoList(relationService.getEagerFollowings(username, RelationStatus.CLOSE_FRIEND));
    }

    @PostMapping("/follow/{relatedUsername}")
    public void followUser(@PathVariable String relatedUsername,
                           @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Follow user: " + relatedUsername, username);
        relationService.followUser(username, relatedUsername);
    }

    @PutMapping("/accept/{relatedUsername}")
    public void acceptFollower(@PathVariable String relatedUsername,
                               @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Accept follower: " + relatedUsername, username);
        relationService.acceptFollower(username, relatedUsername);
    }

    @PutMapping("/block/{relatedUsername}")
    public void blockUser(@PathVariable String relatedUsername,
                          @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Block user: " + relatedUsername, username);
        relationService.blockUser(username, relatedUsername);
    }

    @PutMapping("/update/{relatedUsername}/{status}")
    public void updateRelation(@PathVariable String relatedUsername, @PathVariable RelationStatus status,
                               @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Update relation with user: " + relatedUsername, username);
        relationService.updateUserRelation(username, relatedUsername, status);
    }

    @PutMapping("/mutePost/{relatedUsername}/{isMuted}")
    public void mutePosts(@PathVariable String relatedUsername, @PathVariable boolean isMuted,
                          @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Update relation with user: " + relatedUsername, username);
        relationService.setRelationBoolean(username, relatedUsername, UserRelation::setMutePost, isMuted);
    }

    @PutMapping("/muteStory/{relatedUsername}/{isMuted}")
    public void muteStories(@PathVariable String relatedUsername, @PathVariable boolean isMuted,
                          @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Update relation with user: " + relatedUsername, username);
        relationService.setRelationBoolean(username, relatedUsername, UserRelation::setMuteStory, isMuted);
    }

    @PutMapping("/notifyPost/{relatedUsername}/{isMuted}")
    public void notifyPost(@PathVariable String relatedUsername, @PathVariable boolean isMuted,
                            @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Update post notifications: " + relatedUsername, username);
        relationService.setRelationBoolean(username, relatedUsername, UserRelation::setNotifyPost, isMuted);
    }

    @PutMapping("/notifyStory/{relatedUsername}/{isMuted}")
    public void notifyStory(@PathVariable String relatedUsername, @PathVariable boolean isMuted,
                           @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Update story notifications: " + relatedUsername, username);
        relationService.setRelationBoolean(username, relatedUsername, UserRelation::setNotifyStory, isMuted);
    }

    @DeleteMapping("/remove/{relatedUsername}")
    public void removeRelation(@PathVariable String relatedUsername,
                               @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Remove relation with user: " + relatedUsername, username);
        relationService.removeUserRelation(username, relatedUsername);
    }

    @DeleteMapping("/removeRequest/{relatedUsername}")
    public void removeRequestRelation(@PathVariable String relatedUsername,
                               @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Remove relation with user: " + relatedUsername, username);
        relationService.removeUserRelation(relatedUsername, username);
    }

    private String getUsernameFromToken(String tokenHeader){
        //String username = jwtUtil.extractUsername(tokenHeader);
        String username = "";
        if (username == null)
            throw new UnauthorizedException("Access denied");
        return username;
    }

}
