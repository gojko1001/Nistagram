package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "${gatewayURL}")
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
    public User create(@RequestBody UserRegistrationDto userReg) {
        return userService.create(userReg);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('EDIT_PROFILE')")
    public User edit(@RequestBody UserDto userDto) {
        log.info("Try to edit user: " + userDto.getPastUsername());
        return userService.updateUser(UserMapper.mapUserDtoToUser(userDto), userDto.getPastUsername());
    }

}
