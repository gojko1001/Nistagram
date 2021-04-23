package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.domain.UserCredentials;
import com.xws.nistagrammonolith.domain.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.service.interfaces.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/userCredentials")
@CrossOrigin(origins = "http://localhost:3000")
public class UserCredentialsController {
    @Autowired
    private IUserCredentialsService userCredentialsService;

    @PostMapping
    public UserCredentials login(@RequestBody UserCredentialsDto userReg) throws IOException {
        //TODO: Generate jwt token on successful login
        return userCredentialsService.login(userReg);
    }
}
