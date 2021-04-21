package com.xws.nistagrammonolith.controller;

import com.xws.nistagrammonolith.service.interfaces.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userCredentials")
@CrossOrigin(origins = "http://localhost:3000")
public class UserCredentialsController {
    @Autowired
    private IUserCredentialsService userCredentialsService;
}
