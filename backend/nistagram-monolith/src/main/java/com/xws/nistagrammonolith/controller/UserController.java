package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "${clientURL}")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    public User getUserByUsername(@PathVariable String username){
        return userService.findUserByUsername(username);
    }

    @PostMapping("/add")
    public User create(@RequestBody UserCredentialsDto userReg) {
        return userService.create(userReg);
    }

    @PostMapping("/verify/{username}")
    public User verifyUser(@PathVariable String username){
        return userService.verifyAccount(username);
    }
}