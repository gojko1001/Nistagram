package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.domain.enums.RelationStatus;
import com.nistagram.usermicroservice.dto.UserDto;
import com.nistagram.usermicroservice.dto.UserRelationDto;
import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.service.IUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user_relation")
@CrossOrigin(origins = "https://localhost:3000")
public class UserRelationController {
    @Autowired
    private IUserRelationService relationService;

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

    @PostMapping("/follow")
    public void followUser(@RequestBody UserRelationDto relationDto){
        Logger.info("Follow user: " + relationDto.getRelatedUsername(), relationDto.getUsername());
        relationService.followUser(relationDto);
    }

    @PutMapping("/accept")
    public void acceptFollower(@RequestBody UserRelationDto relationDto){
        Logger.info("Accept follower: " + relationDto.getRelatedUsername(), relationDto.getUsername());
        relationService.acceptFollower(relationDto);
    }

    @PutMapping("/block")
    public void blockUser(@RequestBody UserRelationDto relationDto){
        Logger.info("Block user: " + relationDto.getRelatedUsername(), relationDto.getUsername());
        relationService.blockUser(relationDto);
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

    @PutMapping("/remove")
    public void removeRelation(@RequestBody UserRelationDto relationDto){
        Logger.info("Remove relation with user: " + relationDto.getRelatedUsername(), relationDto.getUsername());
        relationService.removeUserRelation(relationDto);
    }
}
