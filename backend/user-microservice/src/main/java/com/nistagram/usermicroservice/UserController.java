package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.dto.UserDto;
import com.nistagram.usermicroservice.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.dto.UserUpdateDto;
import com.nistagram.usermicroservice.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "https://localhost:3000")
public class UserController {
// cjel mogu sad da pokrene
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
    public UserUpdateDto updateProfile(@RequestBody UserUpdateDto userUpdateDto, @PathVariable String oldUsername) {
        log.info("Try to edit user: " + oldUsername);
        return UserMapper.mapUserToUserUpdateDto(userService.updateUser(UserMapper.mapUserUpdateDtoToUser(userUpdateDto), oldUsername));
    }

    @GetMapping("/search")
    public List<UserDto> search(@RequestParam String username){
        log.info("Try to search users with username: " + username);
        return UserMapper.mapUserListToUserDtoList(userService.search(username));
    }

}
