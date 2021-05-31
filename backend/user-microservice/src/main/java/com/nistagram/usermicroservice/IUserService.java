package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> getAll();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User create(UserRegistrationDto userReg);

    User registerUser(UserRegistrationDto userReg);

    User updateUser(User user, String oldUsername);

    User save(User user);
}
