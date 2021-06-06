package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.domain.enums.RelationStatus;
import com.nistagram.usermicroservice.dto.UserDto;
import com.nistagram.usermicroservice.dto.UserRelationDto;
import com.nistagram.usermicroservice.service.IUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/userrelation")
@CrossOrigin(origins = "${gatewayURL}")
public class UserRelationController {
    @Autowired
    private IUserRelationService relationService;

    @GetMapping("/followers/{username}")
    private List<UserDto> getFollowers(@PathVariable String username){
        return UserMapper.mapUserListToUserDtoList(relationService.getUserFollowers(username));
    }

    @GetMapping("/followings/{username}")
    private List<UserDto> getFollowings(@PathVariable String username){
        return UserMapper.mapUserListToUserDtoList(relationService.getUserFollowings(username));
    }

    @GetMapping("/blocked/{username}")
    private List<UserDto> getBlockedUsers(@PathVariable String username){
        return UserMapper.mapUserListToUserDtoList(relationService.getEagerFollowings(username, RelationStatus.BLOCKED));
    }

    @GetMapping("/closefriends/{username}")
    private List<UserDto> getCloseFriends(@PathVariable String username){
        return UserMapper.mapUserListToUserDtoList(relationService.getEagerFollowings(username, RelationStatus.CLOSE_FRIEND));
    }

    @PostMapping("/follow")
    public void followUser(@RequestBody UserRelationDto relationDto){
        relationService.followUser(relationDto);
    }

    @PutMapping("/accept")
    public void acceptFollower(@RequestBody UserRelationDto relationDto){
        relationService.acceptFollower(relationDto);
    }

    @PutMapping("/block")
    public void blockUser(@RequestBody UserRelationDto relationDto){
        relationService.blockUser(relationDto);
    }

    @PutMapping("/update")
    public void updateRelation(@RequestBody UserRelationDto relationDto){
        relationService.updateUserRelation(relationDto);
    }

    @PutMapping("/notifications")
    public void updateNotifications(@RequestBody UserRelationDto relationDto){
        relationService.setNotifications(relationDto);
    }

    @DeleteMapping
    public void removeRelation(@RequestBody UserRelationDto relationDto){
        relationService.removeUserRelation(relationDto);
    }
}
