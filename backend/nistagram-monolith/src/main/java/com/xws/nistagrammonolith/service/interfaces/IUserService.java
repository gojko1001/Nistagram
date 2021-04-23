package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.controller.dto.UserCredentialsDto;

import java.util.List;

public interface IUserService {

    List<User> getAll();

    User create(UserCredentialsDto userReg);

    User createUserAndCredentials(UserCredentialsDto userReg);

    User findUserByUsername(String username);
}
