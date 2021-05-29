package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.domain.BlackList;
import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.domain.UserCredentials;
import com.xws.nistagrammonolith.exception.AlreadyExistsException;
import com.xws.nistagrammonolith.exception.BadRequestException;
import com.xws.nistagrammonolith.exception.InvalidActionException;
import com.xws.nistagrammonolith.exception.NotFoundException;
import com.xws.nistagrammonolith.repository.IBlackListRepository;
import com.xws.nistagrammonolith.repository.IUserCredentialsRepository;
import com.xws.nistagrammonolith.repository.IUserRepository;
import com.xws.nistagrammonolith.security.JwtService;
import com.xws.nistagrammonolith.service.interfaces.IRoleService;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserService implements IUserService {

    // TODO: updatePassword

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserCredentialsService userCredentialsService;
    @Autowired
    private IUserCredentialsRepository userCredentialsRepository; //TODO: treba servis pozvati!
    @Autowired
    private IBlackListRepository blackListRepository;   //TODO: treba servis pozvati!
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IRoleService authService;


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(UserCredentialsDto userReg) {
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
                User user = createUserAndCredentials(userReg);
                emailService.verificationPassword(user);
                return user;
            }
            throw new BadRequestException("Email or password is in invalid format.");
        } catch (Exception e) {
            throw new BadRequestException("Thread " + e.getMessage());
        }
    }

    public Boolean checkUsername(UserCredentialsDto userCredentialsDto) {
        Pattern patternUsername = Pattern.compile("^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{0,29}$");
        return patternUsername.matcher(userCredentialsDto.getUsername()).matches();
    }

    public Boolean checkFullName(UserCredentialsDto userCredentialsDto) {
        Pattern patternFullName = Pattern.compile("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$");
        return patternFullName.matcher(userCredentialsDto.getFullName()).matches();
    }

    public User createUserAndCredentials(UserCredentialsDto userReg) {
        User user = new User();
        UserCredentials userCredentials = new UserCredentials();

        List<BlackList> blackList = blackListRepository.findAll();
        for (BlackList b : blackList) {
            if (b.getPassword().equalsIgnoreCase(userReg.getPassword())) {
                throw new InvalidActionException("You can't use this password, it is in top 20 most common passwords");
            }
        }

        if (!userCredentialsService.isPassword(userReg.getPassword(), userReg.getRepeatPassword())) {
            throw new BadRequestException("Passwords are not the same.");
        }

        String encodedPassword = passwordEncoder.encode(userReg.getPassword());
        userCredentials.setPassword(encodedPassword);
        userCredentials.setUsername(userReg.getUsername());
        userCredentials.setRoles(authService.findByName("ROLE_USER"));
        userCredentials.setVerified(false);
        userCredentialsService.create(userCredentials);
        user.setUsername(userReg.getUsername());
        user.setEmail(userReg.getEmail());
        user.setFullName(userReg.getFullName());
        log.info("Try to save user with username: " + user.getUsername());
        return userRepository.save(user);
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

    public User edit(User user, String pastUsername) {
        User dbUser = findUserByUsername(pastUsername);
        dbUser.setFullName(user.getFullName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhone(user.getPhone());
        dbUser.setUserGender(user.getUserGender());
        dbUser.setBirthDate(user.getBirthDate());
        dbUser.setUsername(user.getUsername());
        dbUser.setWebSite(user.getWebSite());
        dbUser.setBio(user.getBio());
        log.info("Try to save updated user: " + pastUsername);
        return userRepository.save(dbUser);
    }

    public User save(User user) {
        log.info("Try to save user with username: " + user.getUsername());
        return userRepository.save(user);
    }

    //TODO: Nepotrebna metoda?
    public User updateUser(User user) {
        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser != null) {
            dbUser.setBio(user.getBio());
            dbUser.setBirthDate(user.getBirthDate());
            dbUser.setPhone(user.getPhone());
            dbUser.setUserGender(user.getUserGender());
            dbUser.setBio(user.getBio());
            dbUser.setUsername(user.getUsername());
            dbUser.setPublicProfile(user.isPublicProfile());
            dbUser.setPublicDM(user.isPublicDM());
            dbUser.setTaggable(user.isTaggable());
            dbUser.setWebSite(user.getWebSite());
        }
        return dbUser;
    }

    public boolean patternChecker(String email, String password) {
        Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Pattern patternPass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@*:%-_#.&;,+])(?=\\S+$).{8,}");
        return (pattern.matcher(email).matches() && patternPass.matcher(password).matches());
    }

    public String verifyAccount(String username) {
        String extractedUsername = jwtService.extractUsername(username);
        UserCredentials userCredentials = userCredentialsService.findByUsername(extractedUsername);
        if (userCredentials != null) {
            userCredentials.setVerified(true);
        }
        userCredentialsRepository.save(userCredentials);
        return "Your account has been verified successfully";
    }

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.info("There is no user with email: " + email);
            throw new NotFoundException("User with email: " + email + " doesn't exists.");
        }
        return user;
    }

}
