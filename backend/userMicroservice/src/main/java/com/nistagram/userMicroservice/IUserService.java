package com.nistagram.userMicroservice;

import com.nistagram.userMicroservice.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> getAll();

    User create(UserCredentialsDto userReg);

    User createUserAndCredentials(UserCredentialsDto userReg);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User updateUser(User user);

    User edit(User user, String pastUsername);

    User save(User user);
}
