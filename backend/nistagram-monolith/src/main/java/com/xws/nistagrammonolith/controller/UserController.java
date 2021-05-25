package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.controller.dto.UserDto;
import com.xws.nistagrammonolith.controller.mapping.UserMapper;
import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.service.UserService;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "${clientURL}")
public class UserController {

    private IUserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ALL_USERS')")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('GET_USER')")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping("/add")
    public User create(@RequestBody UserCredentialsDto userReg) {
        return userService.create(userReg);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('GET_USER')")
    public User edit(@RequestBody UserDto userDto) {
        log.info("Try to edit user: " + userDto.getPastUsername());
        return userService.edit(UserMapper.mapUserDtoToUser(userDto), userDto.getPastUsername());
    }

}
