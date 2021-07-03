package com.agent.webshop.controller.mapper;

import com.agent.webshop.controller.dto.UserRegistrationDto;
import com.agent.webshop.domain.User;
import com.agent.webshop.domain.UserCredentials;

public class UserMapper {
    public static User mapUserRegistrationDtoToUser(UserRegistrationDto regDto) {
        User user = new User();
        user.setUsername(regDto.getUsername());
        user.setFullName(regDto.getWebSite());
        user.setEmail(regDto.getEmail());
        return user;
    }

    public static UserCredentials mapUserRegistrationDtoToUserCredentials(UserRegistrationDto regDto) {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername(regDto.getUsername());
        userCredentials.setPassword(regDto.getPassword());
        return userCredentials;
    }
}
