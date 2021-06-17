package com.nistagram.usermicroservice.service;

import com.nistagram.usermicroservice.controller.mapper.UserMapper;
import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.domain.UserRelation;
import com.nistagram.usermicroservice.controller.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.repository.IUserRepository;
import com.nistagram.usermicroservice.controller.exception.*;
import com.nistagram.usermicroservice.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService {

    // TODO: updatePassword - authentication microservice

    @Autowired
    private IUserRepository userRepository;

    public List<User> getAll() {
        Logger.infoDb("Read all users from database.");
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findUserByUsername(String username) {
        Logger.infoDb("Try to find " + username + " user in database.");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            Logger.infoDb("There is no user with username: " + username);
            throw new NotFoundException("There is no user with username " + username);
        }
        return user;
    }

    public User findUserByEmail(String email) {
        Logger.infoDb("Try to find " + email + " user in database.");
        User user = userRepository.findByEmail(email);
        if (user == null) {
            Logger.infoDb("There is no user with email: " + email);
            throw new NotFoundException("User with email: " + email + " doesn't exists.");
        }
        return user;
    }

    public User registerUser(UserRegistrationDto userReg, boolean isGoogleUser) {
        if (!isGoogleUser)
            verifyUserInput(userReg);
        User user = UserMapper.mapUserRegistrationDtoToUser(userReg);
//                emailService.verificationPassword(user); TODO: Notification Microservice
        user.setUserRelations(new ArrayList<UserRelation>());
        user.setInvertedRelations(new ArrayList<UserRelation>());
        return userRepository.save(user);
    }

    public User updateUser(User user, String oldUsername) {
        if (findUserByUsername(user.getUsername()) != null)
            throw new InvalidActionException("User with username: " + user.getUsername() + " already exists!");
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
        return save(dbUser);
    }

    public User save(User user) {
        Logger.infoDb("Try to save user with username: " + user.getUsername());
        User dbUser = new User();
        try {
            dbUser = userRepository.save(user);
        } catch (Exception e) {
            Logger.errorDb("Cannot save user: " + user.getUsername(), e.getMessage());
        }
        return dbUser;
    }

    public List<User> search(String username) {
        Logger.infoDb("Search: finding users whose username contains " + username);
        return userRepository.search(username);
    }

    public List<String> arePublic(List<String> usernames) {
        List<User> users = getAll();
        List<String> publicUsers = new ArrayList<>();
        for (String username : usernames) {
            for (User u : users) {
                if (u.getUsername().equals(username) && u.isPublicProfile())
                    publicUsers.add(username);
            }
        }
        return publicUsers;
    }

    public List<String> getPublicUsers() {
        List<String> usernames = new ArrayList<>();
        for (User u : getAll()) {
            if (u.isPublicProfile())
                usernames.add(u.getUsername());
        }
        return usernames;
    }

    private void verifyUserInput(UserRegistrationDto userReg) {
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
            if (!userReg.getPassword().equals(userReg.getRepeatPassword()))
                throw new BadRequestException("Password and repeat password are not the same.");
            if (!patternChecker(userReg.getEmail(), userReg.getPassword()))
                throw new BadRequestException("Email or password is in invalid format.");
        } catch (Exception e) {
            throw new BadRequestException("Thread " + e.getMessage());
        }
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
