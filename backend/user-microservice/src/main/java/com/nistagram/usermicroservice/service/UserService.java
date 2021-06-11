package com.nistagram.usermicroservice.service;

import com.nistagram.usermicroservice.IUserRepository;
import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.exception.AlreadyExistsException;
import com.nistagram.usermicroservice.exception.BadRequestException;
import com.nistagram.usermicroservice.exception.InvalidActionException;
import com.nistagram.usermicroservice.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserService implements IUserService {

    // TODO: updatePassword - authentication microservice

    @Autowired
    private IUserRepository userRepository;
//    @Autowired
//    private UserCredentialsService userCredentialsService;
//    @Autowired
//    private EmailService emailService;


    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null){
            log.info("There is no user with username: " + username);
            throw new NotFoundException("There is no user with username " + username);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.info("There is no user with email: " + email);
            throw new NotFoundException("User with email: " + email + " doesn't exists.");
        }
        return user;
    }

    public User create(UserRegistrationDto userReg) {
        try {
            User newUser = userRepository.findByUsername(userReg.getUsername());
            if (newUser != null)
                throw new AlreadyExistsException(String.format("User with username %s, already exists", userReg.getUsername()));
            if (userRepository.findByEmail(userReg.getEmail()) != null)
                throw new AlreadyExistsException(String.format("User with email %s, already exists", userReg.getEmail()));
            if (!checkUsername(userReg))
                throw new BadRequestException("Username is in invalid format.");
            if (!checkFullName(userReg))
                throw new BadRequestException("Full name is in invalid format.");
            if (!userReg.getPassword().equals(userReg.getRepeatPassword())) {
                throw new BadRequestException("Password and repeat password are not the same.");
            }
            if (patternChecker(userReg.getEmail(), userReg.getPassword())) {
                User user = registerUser(userReg);
//                emailService.verificationPassword(user); TODO: Notification Microservice
                return user;
            }
            throw new BadRequestException("Email or password is in invalid format.");
        } catch (Exception e) {
            throw new BadRequestException("Thread " + e.getMessage());
        }
    }

    public User registerUser(UserRegistrationDto userReg) {
        if (!userReg.getPassword().equals(userReg.getRepeatPassword())) {
            throw new BadRequestException("Passwords are not the same.");
        }
//        userCredentialsService.create(userReg); TODO: Authentication service: create(UserCredentials userCredentails)
        User user = new User();
        user.setUsername(userReg.getUsername());
        user.setEmail(userReg.getEmail());
        user.setFullName(userReg.getFullName());
        log.info("Try to save user with username: " + user.getUsername());
        return userRepository.save(user);
    }

    public User updateUser(User user, String oldUsername) {
        User dbUser = findUserByUsername(oldUsername);
        dbUser.setBio(user.getBio());
        dbUser.setBirthDate(user.getBirthDate());
        dbUser.setPhone(user.getPhone());
        dbUser.setUserGender(user.getUserGender());
        dbUser.setUsername(user.getUsername());
        dbUser.setWebSite(user.getWebSite());
        dbUser.setPublicProfile(user.isPublicProfile());
        dbUser.setPublicDM(user.isPublicDM());
        dbUser.setTaggable(user.isTaggable());
        log.info("Try to save updated user: " + oldUsername);
        return userRepository.save(dbUser);
    }

    public User save(User user) {
        log.info("Try to save user with username: " + user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public List<User> search(String username) {
        return userRepository.search(username);
    }


    private boolean checkUsername(UserRegistrationDto userRegistrationDto) {
        Pattern patternUsername = Pattern.compile("^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{0,29}$");
        return patternUsername.matcher(userRegistrationDto.getUsername()).matches();
    }

    private boolean checkFullName(UserRegistrationDto userRegistrationDto) {
        Pattern patternFullName = Pattern.compile("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$");
        return patternFullName.matcher(userRegistrationDto.getFullName()).matches();
    }

    private boolean patternChecker(String email, String password) {
        Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Pattern patternPass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@*:%-_#.&;,+])(?=\\S+$).{8,}");
        return (pattern.matcher(email).matches() && patternPass.matcher(password).matches());
    }
}
