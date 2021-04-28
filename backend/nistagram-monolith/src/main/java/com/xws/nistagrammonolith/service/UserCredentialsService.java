package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.ResetPasswordDto;
import com.xws.nistagrammonolith.domain.BlackList;
import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.domain.UserCredentials;
import com.xws.nistagrammonolith.controller.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.exception.BadRequestException;
import com.xws.nistagrammonolith.exception.InvalidActionException;
import com.xws.nistagrammonolith.exception.NotFoundException;
import com.xws.nistagrammonolith.repository.IBlackListRepository;
import com.xws.nistagrammonolith.repository.IUserCredentialsRepository;
import com.xws.nistagrammonolith.security.JwtService;
import com.xws.nistagrammonolith.service.interfaces.IUserCredentialsService;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xws.nistagrammonolith.util.Base64Utility;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@Service
public class UserCredentialsService implements IUserCredentialsService {
    @Autowired
    private IUserCredentialsRepository userCredentialsRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IBlackListRepository blackListRepository;

    public UserCredentials create(UserCredentials userCredentials){
        return userCredentialsRepository.save(userCredentials);
    }

    public UserCredentials login(UserCredentialsDto userCredentialsDto) throws IOException {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(userCredentialsDto.getUsername());
        if(userCredentials.getVerified()==false){
            throw new InvalidActionException("The account must be verified.");
        }
        if(userCredentials != null){
            String hashEnteredPassword = hashPassword(userCredentialsDto.getPassword(), Base64Utility.decode(userCredentials.getSalt()));
            if(isPassword(userCredentials.getPassword(), hashEnteredPassword)) {
                return userCredentials;
            }
        }
        throw new BadRequestException("Username or password is not correct.");
    }

    public UserCredentials findByUsername(String username){
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
        if (userCredentials == null)
            throw new NotFoundException("There is no user credentials with username "+username);
        return userCredentials;
    }


    public void restartPassword(String jwt, ResetPasswordDto resetPasswordDto) {
        String extractedUsername = jwtService.extractUsername(jwt);
        UserCredentials userCredentials = findByUsername(extractedUsername);
        if(!isPassword(resetPasswordDto.getPassword(), resetPasswordDto.getRepeatPassword())){
            throw new BadRequestException("Passwords are not the same.");
        }
            byte[] salt = makeSalt();
            userCredentials.setSalt(Base64Utility.encode(salt));

            List<BlackList> blackList = blackListRepository.findAll();
            for(BlackList b: blackList){
                if(b.getPassword().equalsIgnoreCase(resetPasswordDto.getPassword())){
                    throw new InvalidActionException("You can't use this password, it is in top 20 most common passwords");
                }
            }

            userCredentials.setPassword(hashPassword(resetPasswordDto.getPassword(), salt));

            userCredentialsRepository.save(userCredentials);

    }

    public void sendResetPasswordLink(String email) {
        User user = userService.findUserByEmail(email);
        emailService.resetPassword(user);
    }

    public boolean isPassword(String password1, String password2){
        return password1.equals(password2);
    }

    public String hashPassword(String password, byte[] salt) {
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

    public byte[] makeSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

}
