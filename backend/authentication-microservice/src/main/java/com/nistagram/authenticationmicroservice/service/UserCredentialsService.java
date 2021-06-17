package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.connection.UserConnection;
import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationmicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationmicroservice.dto.UserDto;
import com.nistagram.authenticationmicroservice.exception.BadRequestException;
import com.nistagram.authenticationmicroservice.exception.InvalidActionException;
import com.nistagram.authenticationmicroservice.exception.NotFoundException;
import com.nistagram.authenticationmicroservice.exception.UnauthorizedException;
import com.nistagram.authenticationmicroservice.logger.Logger;
import com.nistagram.authenticationmicroservice.repoistory.IUserCredentialsRepository;
import com.nistagram.authenticationmicroservice.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Pattern;

@Service
public class UserCredentialsService implements IUserCredentialsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserCredentialsRepository userCredentialsRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IBlacklistService blackListService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private UserConnection userConnection;
    @Autowired
    private EmailService emailService;

    @Override
    public UserCredentials findByUsername(String username) {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
        if (userCredentials == null) {
            Logger.infoDb("Can not find user credentials with username: " + username);
            throw new NotFoundException("There is no user credentials with username " + username);
        }
        return userCredentials;
    }

    @Override
    public UserCredentials create(UserCredentialsDto userCredentialsDto) {
        blackListService.isBlacklisted(userCredentialsDto.getPassword());
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername(userCredentialsDto.getUsername());
        userCredentials.setPassword(passwordEncoder.encode(userCredentialsDto.getPassword()));
        userCredentials.setRoles(roleService.findByName("ROLE_USER"));
        userCredentials.setVerified(false);
        try{
            userConnection.registerUser(userCredentialsDto);
        }catch (Exception e){
            throw new BadRequestException("Unsuccessfully added user!");
        }
        Logger.infoDb("Try to save user credentials with username: " + userCredentials.getUsername());
        return userCredentialsRepository.save(userCredentials);
    }

    @Override
    public UserCredentials login(String username, String password) {
        Logger.infoDb("Try to find user credentials with username: " + username);
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
        if (userCredentials == null || !passwordEncoder.matches(password, userCredentials.getPassword()))
            throw new BadRequestException("Username or password is not correct.");
        if (!userCredentials.getVerified())
            throw new InvalidActionException("The account must be verified.");
        return userCredentials;
    }

    @Override
    public UserCredentials loginGoogle(LoginGoogleDto loginGoogleDto) throws IOException {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(loginGoogleDto.getEmail().split("@")[0]);
        if (userCredentials != null)
            return userCredentials;
        userCredentials = new UserCredentials();
        userCredentials.setUsername(loginGoogleDto.getEmail().split("@")[0]);
        userCredentials.setPassword(passwordEncoder.encode(RandomString.make(10)));
        userCredentials.setRoles(roleService.findByName("ROLE_USER"));
        userCredentials.setVerified(true);

        UserCredentialsDto userCredentialsDto = new UserCredentialsDto();
        userCredentialsDto.setUsername(userCredentials.getUsername());
        userCredentialsDto.setFullName(loginGoogleDto.getName());
        userCredentialsDto.setEmail(loginGoogleDto.getEmail());
        try{
            userConnection.registerGoogleUser(userCredentialsDto);
        }catch (Exception e){
            throw new BadRequestException("Unsuccessfully added user!");
        }
//        emailService.resetPassword(user); TODO: Notification microservice
        return userCredentialsRepository.save(userCredentials);

    }

    @Override
    public void changePassword(ResetPasswordDto resetPasswordDto, String jwt){
        String username = jwtService.extractUsername(jwt.substring(7));
        if(username == null){
            throw new UnauthorizedException("Bad credentials!");
        }
        login(username, resetPasswordDto.getOldPassword());
        if(!resetPasswordDto.getPassword().equals(resetPasswordDto.getRepeatPassword()))
            throw new BadRequestException("Repeated password is incorrect!");
        blackListService.isBlacklisted(resetPasswordDto.getPassword());
        if(!passwordPatternChecker(resetPasswordDto.getPassword()))
            throw new BadRequestException("Password is too weak");

        UserCredentials user = userCredentialsRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
        userCredentialsRepository.save(user);
    }

    @Override
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

    @Override
    public void sendResetPasswordLink(String email) {
        UserDto user = userConnection.getUserByEmail(email);
        emailService.resetPassword(user.getUsername(), user.getEmail(), user.getFullName());
    }

    @Override
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

    private boolean passwordPatternChecker(String password) {
        Pattern patternPass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@*:%-_#.&;,+])(?=\\S+$).{8,}");
        return patternPass.matcher(password).matches();
    }
}
