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
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{username}")
//    @PreAuthorize("hasAuthority('GET_USER')")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping("/add")
    public User create(@RequestBody UserRegistrationDto userReg) {
        return userService.create(userReg);
    }

    @PutMapping
//    @PreAuthorize("hasAuthority('EDIT_PROFILE')")
    public User edit(@RequestBody UserDto userDto) {
        log.info("Try to edit user: " + userDto.getPastUsername());
        return userService.updateUser(UserMapper.mapUserDtoToUser(userDto), userDto.getPastUsername());
    }

}
