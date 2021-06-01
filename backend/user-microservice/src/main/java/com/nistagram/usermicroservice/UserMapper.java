package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.domain.User;

public class UserMapper {
    public static User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setUserGender(userDto.getUserGender());
        user.setBirthDate(userDto.getBirthDate());
        user.setUsername(userDto.getUsername());
        user.setWebSite(userDto.getWebSite());
        user.setBio(userDto.getBio());
        return user;
    }
}
