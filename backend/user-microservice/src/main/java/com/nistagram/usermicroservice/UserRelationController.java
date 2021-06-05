package com.nistagram.usermicroservice;

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

    @GetMapping("/followings/{username}")
    private List<UserDto> getFollowings(@PathVariable String username){
        return UserMapper.mapUserListToUserDtoList(relationService.getUserFollowings(username));
    }

    @GetMapping("/followers/{username}")
    private List<UserDto> getFollowers(@PathVariable String username){
        return UserMapper.mapUserListToUserDtoList(relationService.getUserFollowers(username));
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
    public void UpdateRelation(@RequestBody UserRelationDto relationDto){
        relationService.updateUserRelation(relationDto);
    }

    @DeleteMapping()
    public void removeRelation(@RequestBody UserRelationDto relationDto){
        relationService.removeUserRelation(relationDto);
    }
}
