package com.nistagram.usermicroservice;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

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

    public static UserDto mapUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setWebSite(user.getWebSite());
        userDto.setBio(user.getBio());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setUserGender(user.getUserGender());
        return userDto;
    }

    public static List<UserDto> mapUserListToUserDtoList(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();
        for(User u : users)
            userDtos.add(mapUserToUserDto(u));
        return userDtos;
    }
}
