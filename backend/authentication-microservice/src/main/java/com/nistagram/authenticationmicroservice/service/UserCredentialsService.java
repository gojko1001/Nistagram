package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationmicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationmicroservice.exception.BadRequestException;
import com.nistagram.authenticationmicroservice.exception.InvalidActionException;
import com.nistagram.authenticationmicroservice.exception.NotFoundException;
import com.nistagram.authenticationmicroservice.repoistory.IUserCredentialsRepository;
import com.nistagram.authenticationmicroservice.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class UserCredentialsService implements IUserCredentialsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserCredentialsRepository userCredentialsRepository;
    //    @Autowired
//    private IUserService userService;
//    @Autowired
//    private EmailService emailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IBlacklistService blackListService;
    @Autowired
    private IRoleService roleService;

    public UserCredentials findByUsername(String username) {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
        if (userCredentials == null) {
            log.info("Can not find user credentials with username: " + username);
            throw new NotFoundException("There is no user credentials with username " + username);
        }
        return userCredentials;
    }

    public UserCredentials create(UserCredentials userCredentials) {
        blackListService.isBlacklisted(userCredentials.getPassword());
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        userCredentials.setRoles(roleService.findByName("ROLE_USER"));
        userCredentials.setVerified(false);
        log.info("Try to save user credentials with username: " + userCredentials.getUsername());
        return userCredentialsRepository.save(userCredentials);
    }

    public UserCredentials login(UserCredentialsDto userCredentialsDto) throws IOException {
        log.info("Try to find user credentials with username: " + userCredentialsDto.getUsername());
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(userCredentialsDto.getUsername());
        if (userCredentials == null || !passwordEncoder.matches(userCredentialsDto.getPassword(), userCredentials.getPassword()))
            throw new BadRequestException("Username or password is not correct.");
        if (!userCredentials.getVerified())
            throw new InvalidActionException("The account must be verified.");
        return userCredentials;
    }

    public UserCredentials loginGoogle(LoginGoogleDto loginGoogleDto) throws IOException {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(loginGoogleDto.getEmail());
        if (userCredentials != null)
            return userCredentials;
        userCredentials = new UserCredentials();
        String encodedPassword = passwordEncoder.encode(RandomString.make(10));
        userCredentials.setPassword(encodedPassword);
        userCredentials.setUsername(loginGoogleDto.getEmail().split("@")[0]);
        userCredentials.setVerified(true);
        userCredentials.setRoles(roleService.findByName("ROLE_USER"));

//        User user = new User();
//        user.setUsername(userCredentials.getUsername());
//        user.setFullName(loginGoogleDto.getName());
//        user.setEmail(loginGoogleDto.getEmail());
//
//        userService.save(user);           TODO: Premestiti u user microservice

//        emailService.resetPassword(user); TODO: Notification microservice
        return create(userCredentials);

    }

    public void restartPassword(String jwt, ResetPasswordDto resetPasswordDto) {
        if (!isPassword(resetPasswordDto.getPassword(), resetPasswordDto.getRepeatPassword())) {
            throw new BadRequestException("Passwords are not the same.");
        }
        blackListService.isBlacklisted(resetPasswordDto.getPassword());

        String extractedUsername = jwtService.extractUsername(jwt);
        UserCredentials userCredentials = findByUsername(extractedUsername);
        String encodedPassword = passwordEncoder.encode(resetPasswordDto.getPassword());
        userCredentials.setPassword(encodedPassword);
        userCredentialsRepository.save(userCredentials);
    }

    public void sendResetPasswordLink(String email) {
//        User user = userService.findUserByEmail(email);
//        emailService.resetPassword(user);         TODO: User microservice?
    }

    public String verifyAccount(String username) {
        String extractedUsername = jwtService.extractUsername(username);
        UserCredentials userCredentials = findByUsername(extractedUsername);
        if (userCredentials != null) {
            userCredentials.setVerified(true);
        }
        userCredentialsRepository.save(userCredentials);
        return "Your account has been verified successfully";
    }

    private boolean isPassword(String password1, String password2) {
        return password1.equals(password2);
    }

}
