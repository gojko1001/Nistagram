package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.connection.UserConnection;
import com.nistagram.authenticationmicroservice.domain.Role;
import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.dto.*;
import com.nistagram.authenticationmicroservice.exception.BadRequestException;
import com.nistagram.authenticationmicroservice.exception.InvalidActionException;
import com.nistagram.authenticationmicroservice.exception.NotFoundException;
import com.nistagram.authenticationmicroservice.exception.UnauthorizedException;
import com.nistagram.authenticationmicroservice.logger.Logger;
import com.nistagram.authenticationmicroservice.repoistory.IUserCredentialsRepository;
import com.nistagram.authenticationmicroservice.saga.event.GetUsernameEvent;
import com.nistagram.authenticationmicroservice.saga.event.MessageFactory;
import com.nistagram.authenticationmicroservice.saga.event.UpdateUsernameEvent;
import com.nistagram.authenticationmicroservice.saga.eventListener.AuthUpdateUsernameListener;
import com.nistagram.authenticationmicroservice.saga.eventListener.CancelUpdateListener;
import com.nistagram.authenticationmicroservice.security.JwtService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    @Autowired
    private AuthUpdateUsernameListener updateUsernameListener;
    @Autowired
    private CancelUpdateListener cancelUpdateListener;

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
        try {
            userConnection.registerUser(userCredentialsDto);
        } catch (Exception e) {
            throw new BadRequestException("Unsuccessfully added user!");
        }
        Logger.infoDb("Try to save user credentials with username: " + userCredentials.getUsername());
        return userCredentialsRepository.save(userCredentials);
    }

    @Override
    public UserCredentials createAgent(UserCredentialsAgentDto userCredentials) {
        UserCredentials userCredentials1 = new UserCredentials();
        userCredentials1.setUsername(userCredentials.getUsername());
        userCredentials1.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        userCredentials1.setRoles(roleService.findByName("ROLE_AGENT"));
        userCredentials1.setVerified(false);
        try {
            userConnection.registerAgent(userCredentials);
        } catch (Exception e) {
            throw new BadRequestException("Unsuccessfully added user!");
        }
        Logger.infoDb("Try to save user credentials with username: " + userCredentials.getUsername());
        return userCredentialsRepository.save(userCredentials1);
    }

    @Override
    public UserCredentials login(String username, String password) {
        Logger.infoDb("Try to find user credentials with username: " + username);
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
        if (userCredentials == null || !passwordEncoder.matches(password, userCredentials.getPassword()))
            throw new BadRequestException("Username or password is not correct.");
        if (!userCredentials.getVerified())
            throw new InvalidActionException("The account must be verified.");
        if (userCredentials.getIsDeactivated())
            throw new InvalidActionException("Your account is deactivated by admin");
        return userCredentials;
    }

    @Override
    public UserCredentials loginGoogle(LoginGoogleDto loginGoogleDto) {
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
        try {
            userConnection.registerGoogleUser(userCredentialsDto);
        } catch (Exception e) {
            throw new BadRequestException("Unsuccessfully added user!");
        }
//        emailService.resetPassword(user); TODO: Notification microservice
        return userCredentialsRepository.save(userCredentials);

    }

    @Override
    public List<String> findCredentialsByRoleUserAndAgent() {
        List<UserCredentials> userCredentials = userCredentialsRepository.findAll();
        List<String> userCredentials1 = new ArrayList<>();
        for (UserCredentials uc : userCredentials) {
            if (!uc.getIsDeactivated()) {
                Collection<Role> roles = uc.getRoles();
                for (Role r : roles) {
                    if (r.getName().equals("ROLE_USER") || r.getName().equals("ROLE_AGENT")) {
                        userCredentials1.add(uc.getUsername());
                    }
                }
            }
        }

        return userCredentials1;

    }

    @Override
    public void changeUsername(String oldUsername, String newUsername) {
        UserCredentials credentials = findByUsername(oldUsername);
        credentials.setUsername(newUsername);
        userCredentialsRepository.save(credentials);
    }

    @Override
    public void changePassword(ResetPasswordDto resetPasswordDto, String jwt) {
        String username = jwtService.extractUsername(jwt.substring(7));
        if (username == null) {
            throw new UnauthorizedException("Bad credentials!");
        }
        login(username, resetPasswordDto.getOldPassword());
        if (!resetPasswordDto.getPassword().equals(resetPasswordDto.getRepeatPassword()))
            throw new BadRequestException("Repeated password is incorrect!");
        blackListService.isBlacklisted(resetPasswordDto.getPassword());
        if (!passwordPatternChecker(resetPasswordDto.getPassword()))
            throw new BadRequestException("Password is too weak");

        UserCredentials user = userCredentialsRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
        userCredentialsRepository.save(user);
    }

    @Override
    public void restartPassword(String jwt, ResetPasswordDto resetPasswordDto) {
        if (!resetPasswordDto.getPassword().equals(resetPasswordDto.getRepeatPassword())) {
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
    public void deactivateProfile(String username) {
        UserCredentials userCredentials = findByUsername(username);
        userCredentials.setIsDeactivated(true);
        userCredentialsRepository.save(userCredentials);
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

    @Override
    @Transactional
    public void newUpdateUsername(GetUsernameEvent event) {
        UserCredentials credentials = findByUsername(event.getOldUsername());
        credentials.setUsername(event.getNewUsername());

        try {
            userCredentialsRepository.save(credentials);
            updateUsernameListener.sendMessageToNewUpdate(
                    MessageFactory.createUpdateUsernameMessage(event.getNewUsername(), event.getOldUsername()));
        } catch (Exception e) {
            e.printStackTrace();
            cancelUpdateListener.sendMessageToNewUpdate(
                    MessageFactory.createUpdateUsernameMessage(event.getNewUsername(), event.getOldUsername()));
        }
    }

    @Override
    @Transactional
    public void usernameUpdateDone(GetUsernameEvent event) {
        UserCredentials userCredentials = findByUsername(event.getNewUsername());
        UserCredentials user = findByUsername(event.getOldUsername());
        if (userCredentials == null && user != null) {
            user.setUsername(event.getNewUsername());
            userCredentialsRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void cancelUpdateUsername(GetUsernameEvent event) {
        UserCredentials userCredentials = findByUsername(event.getNewUsername());
        if (userCredentials != null) {
            userCredentials.setUsername(event.getOldUsername());
            userCredentialsRepository.save(userCredentials);
        }
    }

    private boolean passwordPatternChecker(String password) {
        Pattern patternPass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@*:%-_#.&;,+])(?=\\S+$).{8,}");
        return patternPass.matcher(password).matches();
    }
}
