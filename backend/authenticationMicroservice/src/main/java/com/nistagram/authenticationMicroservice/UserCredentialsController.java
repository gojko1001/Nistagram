package com.nistagram.authenticationMicroservice;

import com.nistagram.authenticationMicroservice.domain.UserCredentials;
import com.nistagram.authenticationMicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationMicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationMicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationMicroservice.security.JwtService;
import com.nistagram.authenticationMicroservice.service.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/userCredentials")
@CrossOrigin(origins = "${gatewayURL}")
public class UserCredentialsController {
    @Autowired
    private IUserCredentialsService userCredentialsService;
    @Autowired
    private JwtService jwtService;

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

    @GetMapping("/send_email/{email}")
    public void sendPasswordLink(@PathVariable String email) throws IOException {
        userCredentialsService.sendResetPasswordLink(email);
    }

    @PutMapping("/reset_password/{jwt}")
    public void restartPassword(@PathVariable String jwt, @RequestBody ResetPasswordDto resetPasswordDto) throws IOException {
        userCredentialsService.restartPassword(jwt, resetPasswordDto);
    }

    @GetMapping("/verify/{username}")
    public String verifyUser(@PathVariable String username) {
        return userCredentialsService.verifyAccount(username);
    }
}
