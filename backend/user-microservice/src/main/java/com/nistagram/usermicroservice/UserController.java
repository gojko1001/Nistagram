package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.dto.UserDto;
import com.nistagram.usermicroservice.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
@CrossOrigin(origins = "https://localhost:3000")
public class UserController {
    // cjel mogu sad da pokrene
    @Autowired
    private IUserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        Logger.info("Get all users", "");
        return UserMapper.mapUserListToUserDtoList(userService.getAll());
    }

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        Logger.info("Get user by username", username);
        return UserMapper.mapUserToUserDto(userService.findUserByUsername(username));
    }

    @PostMapping("/find")
    public UserDto getUserByEmail(@RequestBody String email) {
        return UserMapper.mapUserToUserDto(userService.findUserByEmail(email));
    }

    @PostMapping("/add")
    public void registerUser(@RequestBody UserRegistrationDto userReg) {
        userService.registerUser(userReg, false);
    }

    @PostMapping("/addGoogleUser")
    public void registerGoogleUser(@RequestBody UserRegistrationDto userReg) {
        userService.registerUser(userReg, true);
    }

    @PutMapping("/{oldUsername}")
    public UserDto updateProfile(@RequestBody UserDto userDto, @PathVariable String oldUsername) {
        Logger.info("Try to edit user: " + oldUsername, userDto.getUsername());
        return UserMapper.mapUserToUserDto(userService.updateUser(UserMapper.mapUserDtoToUser(userDto), oldUsername));
    }

    @GetMapping("/search")
    public List<UserDto> search(@RequestParam String username) {
        log.info("Try to search users with username: " + username);
        return UserMapper.mapUserListToUserDtoList(userService.search(username));
    }

    @PostMapping("/are_public")
    public List<String> arePublic(@RequestBody List<String> usernames) {
        return userService.arePublic(usernames);
    }

    @GetMapping("/public_users")
    public List<String> getPublicUsers() {
        log.info("Get all public users");
        return userService.getPublicUsers();
    }
}
