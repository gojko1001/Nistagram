package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.exception.AlreadyExistsException;
import com.xws.nistagrammonolith.exception.BadRequestException;
import com.xws.nistagrammonolith.exception.UnauthorizeException;
import com.xws.nistagrammonolith.repository.IUserRepository;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.Base64Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService {

    // TODO: updatePassword

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
            if (patternChecker(user.getEmail(), user.getPassword())) {
                byte[] salt = makeSalt();
                user.setSalt(Base64Utility.encode(salt));
                user.setPassword(hashPassword(user.getPassword(), salt));
                user.setTaggable(true);
                user.setPublicDM(true);
                user.setPublicProfile(true);
                return userRepository.save(user);
            }
            throw new BadRequestException("Email or password is in invalid format.");
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

    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            sha512.update(salt);
            byte[] dataHash = sha512.digest(password.getBytes());
            return Base64Utility.encode(dataHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] makeSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public boolean patternChecker(String email, String password){
        Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Pattern patternPass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
        return (pattern.matcher(email).matches() && patternPass.matcher(password).matches());
    }

    // RAZMISLITII
    /*int strength = 10; // work factor of bcrypt
    BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder(strength, new SecureRandom());
    String encodedPassword = bCryptPasswordEncoder.encode(plainPassword);*/

}
