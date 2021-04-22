package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.domain.dto.UserRegistrationDto;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User create(UserRegistrationDto userReg);
    User createUserAndCredentials(UserRegistrationDto userReg);
}
