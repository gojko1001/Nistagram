package com.nistagram.authenticationmicroservice;

import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationmicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationmicroservice.security.JwtService;
import com.nistagram.authenticationmicroservice.service.EmailService;
import com.nistagram.authenticationmicroservice.service.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/userCredentials")
@CrossOrigin(origins = "https://localhost:3000")
public class UserCredentialsController {
    @Autowired
    private IUserCredentialsService userCredentialsService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentialsDto userReg) throws IOException {
        UserCredentials credentials = userCredentialsService.login(userReg);
        String jwt = jwtService.createToken(credentials.getUsername()/*, credentials.getRoles().get(1)*/);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping("/login_google")
    public ResponseEntity<String> loginGoogle(@RequestBody LoginGoogleDto loginGoogleDto) throws IOException {
        UserCredentials credentials = userCredentialsService.loginGoogle(loginGoogleDto);
        String jwt = jwtService.createToken(credentials.getUsername()/*, credentials.getRoles().get(1)*/);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping("/add")
    public void registerUser(@RequestBody UserCredentialsDto userReg) {
        userCredentialsService.create(userReg);
        emailService.verificationPassword(userReg.getUsername(), userReg.getEmail(), userReg.getFullName());

    }

    @GetMapping("/send_email/{email:.+}")
    public void sendPasswordLink(@PathVariable String email) throws IOException {
        userCredentialsService.sendResetPasswordLink(email);
    }

    @PutMapping("/reset_password/{jwt:.+}")
    public void restartPassword(@PathVariable String jwt, @RequestBody ResetPasswordDto resetPasswordDto) throws IOException {
        userCredentialsService.restartPassword(jwt, resetPasswordDto);
    }

    @GetMapping("/verify/{jwt:.+}")
    public String verifyUser(@PathVariable String jwt) {
        return userCredentialsService.verifyAccount(jwt);
    }
}
