package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.domain.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public User create(@RequestBody UserCredentialsDto userReg) {
        return userService.create(userReg);
    }
}
