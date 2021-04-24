package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.controller.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.domain.UserCredentials;
import com.xws.nistagrammonolith.security.JwtService;
import com.xws.nistagrammonolith.service.interfaces.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/userCredentials")
@CrossOrigin(origins = "${clientURL}")
public class UserCredentialsController {
    @Autowired
    private IUserCredentialsService userCredentialsService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentialsDto userReg) throws IOException {
        UserCredentials credentials = userCredentialsService.login(userReg);
        String jwt = jwtService.createToken(credentials.getUsername(), credentials.getUserRole());
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public void restartPassword(@PathVariable String username) throws IOException {
        userCredentialsService.restartPassword(username);
    }
}
