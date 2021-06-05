package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.dto.UserDto;
import com.nistagram.usermicroservice.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.service.IUserService;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "${gatewayURL}")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ALL_USERS')")
    public List<UserDto> getAll() {
        return UserMapper.mapUserListToUserDtoList(userService.getAll());
    }

    @GetMapping("/{username}")
//    @PreAuthorize("hasAuthority('GET_USER')")
    public UserDto getUserByUsername(@PathVariable String username) {
        return UserMapper.mapUserToUserDto(userService.findUserByUsername(username));
    }

    @PostMapping("/add")
    public UserDto create(@RequestBody UserRegistrationDto userReg) {
        return UserMapper.mapUserToUserDto(userService.create(userReg));
    }

    @PutMapping("/{oldUsername}")
//    @PreAuthorize("hasAuthority('EDIT_PROFILE')")
    public UserDto edit(@RequestBody UserDto userDto, @PathVariable String oldUsername) {
        log.info("Try to edit user: " + oldUsername);
        return UserMapper.mapUserToUserDto(userService.updateUser(UserMapper.mapUserDtoToUser(userDto), oldUsername));
    }

}
