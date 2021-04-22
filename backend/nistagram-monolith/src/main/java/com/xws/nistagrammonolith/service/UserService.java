package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.domain.UserCredentials;
import com.xws.nistagrammonolith.domain.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.exception.AlreadyExistsException;
import com.xws.nistagrammonolith.exception.BadRequestException;
import com.xws.nistagrammonolith.repository.IUserRepository;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.Base64Utility;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService {

    // TODO: updatePassword
    // TODO: repeatNewPassword

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserCredentialsService userCredentialsService;

    public List<User> getAll(){
        List<User> users = userRepository.findAll();
        return users;
    }


    public User create(UserCredentialsDto userReg) {
        try {
            User newUser = userRepository.findByUsername(userReg.getUsername());
            if (newUser != null)
                throw new AlreadyExistsException(String.format("User with username %s, already exists", userReg.getUsername()));
            if(userRepository.findByEmail(userReg.getEmail()) != null){
                throw new AlreadyExistsException(String.format("User with email %s, already exists", userReg.getEmail()));
            }
            if (patternChecker(userReg.getEmail(), userReg.getPassword())) {
                return createUserAndCredentials(userReg);
            }
            throw new BadRequestException("Email or password is in invalid format.");
        }catch (Exception e){
            throw new BadRequestException("Thread " + e.getMessage());
        }
    }

    public User createUserAndCredentials(UserCredentialsDto userReg){
        User user = new User();
        UserCredentials userCredentials = new UserCredentials();
        byte[] salt = userCredentialsService.makeSalt();
        userCredentials.setSalt(Base64Utility.encode(salt));
        userCredentials.setPassword(userCredentialsService.hashPassword(userReg.getPassword(), salt));
        userCredentials.setUsername(userReg.getUsername());
        userCredentialsService.create(userCredentials);
        user.setUsername(userReg.getUsername());
        user.setEmail(userReg.getEmail());
        user.setFullName(userReg.getFullName());
        return userRepository.save(user);
    }

    public User updateUser(User user){
        User dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser != null){
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



    public boolean patternChecker(String email, String password){
        Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Pattern patternPass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
        return (pattern.matcher(email).matches() && patternPass.matcher(password).matches());
    }



}
