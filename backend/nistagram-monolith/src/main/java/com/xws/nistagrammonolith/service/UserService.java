package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.exception.AlreadyExistsException;
import com.xws.nistagrammonolith.exception.BadRequestException;
import com.xws.nistagrammonolith.exception.UnauthorizeException;
import com.xws.nistagrammonolith.repository.IUserRepository;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService {

    // TODO: updatePassword
    // TODO: hash&salt

    @Autowired
    private IUserRepository userRepository;

    public List<User> getAll(){
        List<User> users = userRepository.findAll();
        return users;
    }


    public User create(User user) {
        try {
            User existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null)
                throw new AlreadyExistsException(String.format("User with email %s, already exists", user.getEmail()));
            Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
            Pattern patternPass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
            //if ((pattern.matcher(user.getEmail()).matches() && patternPass.matcher(user.getPassword()).matches()) || user.getPassword().equals("")) {
                //return userRepository.save(user);
            //}
            user.setTaggable(true);
            user.setPublicDM(true);
            user.setPublicProfile(true);
            return userRepository.save(user);
        }catch (Exception e){
            throw new BadRequestException("Thread " + e.getMessage());
        }
    }

    public User updateUser(User user){
        User dbUser = userRepository.findByEmail(user.getEmail());
        if(dbUser != null){
            dbUser.setBio(user.getBio());
            dbUser.setBirthDate(user.getBirthDate());
            dbUser.setPhone(user.getPhone());
            dbUser.setUserGender(user.getUserGender());
            dbUser.setBio(user.getBio());
            dbUser.setUsername(user.getUsername());
            dbUser.setPublicProfile(user.getPublicProfile());
            dbUser.setPublicDM(user.getPublicDM());
            dbUser.setTaggable(user.getTaggable());
            dbUser.setWebSite(user.getWebSite());
        }
        return dbUser;
    }



}
