package com.nistagram.usermicroservice.controller.mapper;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.controller.dto.UserDto;
import com.nistagram.usermicroservice.controller.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.controller.dto.UserUpdateDto;

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

    public static UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setWebSite(user.getWebSite());
        userDto.setBio(user.getBio());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setUserGender(user.getUserGender());
        if(user.getVerificationRequest() != null)
            userDto.setStatus(user.getVerificationRequest().getStatus().name());
        return userDto;
    }

    public static List<UserDto> mapUserListToUserDtoList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : users)
            userDtos.add(mapUserToUserDto(u));
        return userDtos;
    }

    public static User mapUserUpdateDtoToUser(UserUpdateDto updateDto) {
        User user = mapUserDtoToUser(updateDto.getUser());
        user.setPublicProfile(updateDto.isPublicProfile());
        user.setPublicDM(updateDto.isPublicDM());
        user.setTaggable(updateDto.isTaggable());
        return user;
    }

    public static UserUpdateDto mapUserToUserUpdateDto(User user) {
        UserUpdateDto updateDto = new UserUpdateDto();
        updateDto.setUser(mapUserToUserDto(user));
        updateDto.setPublicProfile(user.isPublicProfile());
        updateDto.setPublicDM(user.isPublicDM());
        updateDto.setTaggable(user.isTaggable());
        return updateDto;
    }

    public static User mapUserRegistrationDtoToUser(UserRegistrationDto regDto){
        User user = new User();
        user.setUsername(regDto.getUsername());
        user.setFullName(regDto.getFullName());
        user.setEmail(regDto.getEmail());
        return user;
    }
}
