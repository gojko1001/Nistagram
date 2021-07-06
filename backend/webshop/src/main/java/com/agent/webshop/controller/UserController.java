package com.agent.webshop.controller;

import com.agent.webshop.controller.dto.UserRegistrationDto;
import com.agent.webshop.domain.User;
import com.agent.webshop.domain.UserCredentials;
import com.agent.webshop.security.JwtService;
import com.agent.webshop.service.interfaces.IUserCredentialsService;
import com.agent.webshop.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserCredentialsService userCredentialsService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegistrationDto userReg) {
        return userService.registerUser(userReg);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials userCredentials) throws IOException{
        UserCredentials credentials = userCredentialsService.login(userCredentials);
        String jwt = jwtService.createToken(credentials.getUsername(), credentials.getRole());
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("/info/{username}")
    public User findByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }

}
