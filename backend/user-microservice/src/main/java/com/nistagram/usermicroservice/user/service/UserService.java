package com.nistagram.usermicroservice.user.service;

import com.nistagram.usermicroservice.connection.AuthConnection;
import com.nistagram.usermicroservice.connection.MediaConnection;
import com.nistagram.usermicroservice.exception.AlreadyExistsException;
import com.nistagram.usermicroservice.exception.BadRequestException;
import com.nistagram.usermicroservice.exception.InvalidActionException;
import com.nistagram.usermicroservice.exception.NotFoundException;
import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.user.controller.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.user.controller.mapper.UserMapper;
import com.nistagram.usermicroservice.user.domain.User;
import com.nistagram.usermicroservice.user.domain.UserRelation;
import com.nistagram.usermicroservice.user.repository.IUserRepository;
import com.nistagram.usermicroservice.user.service.interfaces.IUserService;
import com.nistagram.usermicroservice.verify_account.domain.VerificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private AuthConnection authConnection;
    @Autowired
    private MediaConnection mediaConnection;

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

    @Override
    public User findUserByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    public User registerUser(UserRegistrationDto userReg, boolean isGoogleUser) {
        if (!isGoogleUser)
            verifyUserInput(userReg);
        User user = UserMapper.mapUserRegistrationDtoToUser(userReg);
        user.setUserRelations(new ArrayList<UserRelation>());
        user.setInvertedRelations(new ArrayList<UserRelation>());
        return userRepository.save(user);
    }

    public User updateUser(User user, String oldUsername, String jwt) {
        if (!checkUsername(user.getUsername())) {
            throw new BadRequestException("New username doesn't match the pattern!");
        }
        if (userRepository.findByUsername(user.getUsername()) != null && !user.getUsername().equals(oldUsername))
            throw new InvalidActionException("User with username: " + user.getUsername() + " already exists!");
        User dbUser = findUserByUsername(oldUsername);
        dbUser.setUsername(user.getUsername());
        dbUser.setFullName(user.getFullName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhone(user.getPhone());
        dbUser.setWebSite(user.getWebSite());
        dbUser.setBio(user.getBio());
        dbUser.setBirthDate(user.getBirthDate());
        dbUser.setUserGender(user.getUserGender());
        dbUser.setPublicProfile(user.isPublicProfile());
        dbUser.setPublicDM(user.isPublicDM());
        dbUser.setTaggable(user.isTaggable());

        if (!user.getUsername().equals(oldUsername)) {
            authConnection.changeUsername(user.getUsername(), jwt);
            mediaConnection.changeUsername(user.getUsername(), jwt);
        }
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

    public List<String> getPublicTaggableUsers() {
        List<String> usernames = new ArrayList<>();
        for (User u : getAll()) {
            if (u.isPublicProfile() && u.isTaggable())
                usernames.add(u.getUsername());
        }
        return usernames;
    }

    public List<User> getUsersWithVerifyRequestPending() {
        List<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        for (User user : users) {
            if (user.getVerificationRequest() != null && user.getVerificationRequest().getStatus().equals(VerificationStatus.PENDING))
                userList.add(user);
        }
        return userList;
    }

    public List<User> getChatableUsers(String username, String text) {
        List<User> users = new ArrayList<>();
        for(User user: userRepository.findAllByPublicDM(true)){
            if(!user.getUsername().equals(username) && user.getUsername().contains(text))
                users.add(user);
        }
        return users;
    }

    private void verifyUserInput(UserRegistrationDto userReg) {
        try {
            User newUser = userRepository.findByUsername(userReg.getUsername());
            if (newUser != null)
                throw new AlreadyExistsException(String.format("User with username %s, already exists", userReg.getUsername()));
            if (userRepository.findByEmail(userReg.getEmail()) != null)
                throw new AlreadyExistsException(String.format("User with email %s, already exists", userReg.getEmail()));
            if (!checkUsername(userReg.getUsername()))
                throw new BadRequestException("Username is in invalid format.");
            if (!checkFullName(userReg.getFullName()))
                throw new BadRequestException("Full name is in invalid format.");
            if (!userReg.getPassword().equals(userReg.getRepeatPassword()))
                throw new BadRequestException("Password and repeat password are not the same.");
            if (!patternChecker(userReg.getEmail(), userReg.getPassword()))
                throw new BadRequestException("Email or password is in invalid format.");
        } catch (Exception e) {
            throw new BadRequestException("Thread " + e.getMessage());
        }
    }

    private boolean checkUsername(String username) {
        Pattern patternUsername = Pattern.compile("^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{0,29}$");
        return patternUsername.matcher(username).matches();
    }

    private boolean checkFullName(String fullName) {
        Pattern patternFullName = Pattern.compile("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$");
        return patternFullName.matcher(fullName).matches();
    }

    private boolean patternChecker(String email, String password) {
        Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Pattern patternPass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@*:%-_#.&;,+])(?=\\S+$).{8,}");
        return (pattern.matcher(email).matches() && patternPass.matcher(password).matches());
    }


}
