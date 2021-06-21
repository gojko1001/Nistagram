package com.nistagram.authenticationmicroservice;

import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationmicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationmicroservice.exception.UnauthorizedException;
import com.nistagram.authenticationmicroservice.logger.Logger;
import com.nistagram.authenticationmicroservice.security.JwtService;
import com.nistagram.authenticationmicroservice.service.EmailService;
import com.nistagram.authenticationmicroservice.service.IUserCredentialsService;
import com.nistagram.authenticationmicroservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentialsDto userReg) throws IOException {
        Logger.info("Try to login.", userReg.getUsername());
        UserCredentials credentials = userCredentialsService.login(userReg.getUsername(), userReg.getPassword());
        String jwt = jwtService.createToken(credentials.getUsername()/*, credentials.getRoles().get(1)*/);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping("/login_google")
    public ResponseEntity<String> loginGoogle(@RequestBody LoginGoogleDto loginGoogleDto) throws IOException {
        Logger.info("Try to login with google.", loginGoogleDto.getEmail());
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
        Logger.info("Send reset password link.", email);
        userCredentialsService.sendResetPasswordLink(email);
    }

    @PreAuthorize("hasAuthority('CHANGE_PASS')")
    @PutMapping("/change_password")
    public void changePassword(@RequestBody ResetPasswordDto resetPasswordDto,
                               @RequestHeader(value = "Authorization", required = false) String jwt){
        userCredentialsService.changePassword(resetPasswordDto, jwt);
    }

    @PreAuthorize("hasAuthority('CHANGE_USERNAME')")
    @PutMapping("/change_username")
    public void changeUsername(@RequestBody String newUsername,
                               @RequestHeader("Authorization") String jwt){
        String username = jwtService.extractUsername(jwt.substring(7));
        if(username == null)
            throw new UnauthorizedException("Access denied");
        userCredentialsService.changeUsername(username, newUsername);
    }

    @PutMapping("/reset_password/{jwt:.+}")
    public void restartPassword(@PathVariable String jwt, @RequestBody ResetPasswordDto resetPasswordDto) throws IOException {
        Logger.info("Reset password.", jwtService.extractUsername(jwt));
        userCredentialsService.restartPassword(jwt, resetPasswordDto);
    }


    @GetMapping("/verify/{jwt:.+}")
    public String verifyUser(@PathVariable String jwt) {
        Logger.info("Verify user.", jwt);
        return userCredentialsService.verifyAccount(jwt);

    }

    @GetMapping("/isAdmin/{username}")
    public boolean isAdmin(@PathVariable String username){
        return roleService.isAdmin(username);
    }
}
