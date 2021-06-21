package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.exception.UnauthorizedException;
import com.mediamicroservice.mediamicroservice.service.interfaces.IUserService;
import com.mediamicroservice.mediamicroservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtil jwtUtil;


    @PutMapping("/{newUsername}")
    public void changeUsername(@PathVariable String newUsername,
                               @RequestHeader("Authorization") String jwt){
        String username = jwtUtil.extractUsername(jwt);
        if(username == null)
            throw new UnauthorizedException("Access denied");
        userService.changeUsername(newUsername, username);
    }
}
