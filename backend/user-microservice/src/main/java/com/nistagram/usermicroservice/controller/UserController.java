package com.nistagram.usermicroservice.controller;

import com.nistagram.usermicroservice.controller.dto.UserDto;
import com.nistagram.usermicroservice.controller.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.controller.exception.UnauthorizedException;
import com.nistagram.usermicroservice.controller.mapper.UserMapper;
import com.nistagram.usermicroservice.JwtService;
import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.service.interfaces.IUserService;
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
    @Autowired
    private JwtService jwtService;

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

    @PutMapping()
    public UserDto updateProfile(@RequestBody UserDto userDto,
                                 @RequestHeader("Authorization") String jwt) {
        String username = jwtService.extractUsername(jwt);
        if (username == null)
            throw new UnauthorizedException("Access denied");
        Logger.info("Try to edit user: " + username, userDto.getUsername());
        return UserMapper.mapUserToUserDto(userService.updateUser(UserMapper.mapUserDtoToUser(userDto), username, jwt));
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
