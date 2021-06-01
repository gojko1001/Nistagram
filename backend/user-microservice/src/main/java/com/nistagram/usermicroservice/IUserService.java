package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> getAll();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User create(UserCredentialsDto userReg);

    User registerUser(UserCredentialsDto userReg);

    User updateUser(User user);

    User edit(User user, String pastUsername);

    User save(User user);
}
