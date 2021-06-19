package com.nistagram.usermicroservice.controller;

import com.nistagram.usermicroservice.JwtService;
import com.nistagram.usermicroservice.controller.dto.UserDto;
import com.nistagram.usermicroservice.controller.dto.UserRelationDto;
import com.nistagram.usermicroservice.controller.exception.NotFoundException;
import com.nistagram.usermicroservice.controller.exception.UnauthorizedException;
import com.nistagram.usermicroservice.controller.mapper.RelationMapper;
import com.nistagram.usermicroservice.controller.mapper.UserMapper;
import com.nistagram.usermicroservice.domain.UserRelation;
import com.nistagram.usermicroservice.domain.enums.RelationStatus;
import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.service.interfaces.IUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user_relation")
@CrossOrigin(origins = "https://localhost:3000")
public class UserRelationController {
    @Autowired
    private IUserRelationService relationService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/{relatedUsername}")
    public UserRelationDto getUserRelation(@PathVariable String relatedUsername,
                                           @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        UserRelation relation = relationService.findRelation(username, relatedUsername);
        if(relation == null)
            throw new NotFoundException("No relation found!");
        return RelationMapper.mapRelationToRelationDto(relation);
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

    @PutMapping("/update")
    public void updateRelation(@RequestBody UserRelationDto relationDto){
        Logger.info("Update relation with user: " + relationDto.getRelatedUsername(), relationDto.getUsername());
        relationService.updateUserRelation(relationDto);
    }

    @PutMapping("/notifications")
    public void updateNotifications(@RequestBody UserRelationDto relationDto){
        Logger.info("Update notifications", relationDto.getUsername());
        relationService.setNotifications(relationDto);
    }

    @DeleteMapping("/remove/{relatedUsername}")
    public void removeRelation(@PathVariable String relatedUsername,
                               @RequestHeader("Authorization") String jwt) {
        String username = getUsernameFromToken(jwt);
        Logger.info("Remove relation with user: " + relatedUsername, username);
        relationService.removeUserRelation(username, relatedUsername);
    }

    private String getUsernameFromToken(String tokenHeader){
        String username = jwtService.extractUsername(tokenHeader);
        if (username == null)
            throw new UnauthorizedException("Access denied");
        return username;
    }
}
