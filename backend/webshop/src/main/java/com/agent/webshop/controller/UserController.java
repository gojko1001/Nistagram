package com.agent.webshop.controller;

import com.agent.webshop.controller.dto.UserRegistrationDto;
import com.agent.webshop.domain.UserCredentials;
import com.agent.webshop.service.interfaces.IUserCredentialsService;
import com.agent.webshop.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserCredentialsService userCredentialsService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserRegistrationDto userReg) {
        return userService.registerUser(userReg);
    }

    @PostMapping("/login")
    public ResponseEntity registerUser(@RequestBody UserCredentials userCredentials) {
        return userCredentialsService.login(userCredentials);
    }
}
