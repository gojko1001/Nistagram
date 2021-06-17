package com.nistagram.usermicroservice.service;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> getAll();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User registerUser(UserRegistrationDto userReg, boolean isGoogleUser);

    User updateUser(User user, String oldUsername, String jwt);

    User save(User user);

    List<User> search(String username);

    List<String> arePublic(List<String> usernames);

    List<String> getPublicUsers();
}
