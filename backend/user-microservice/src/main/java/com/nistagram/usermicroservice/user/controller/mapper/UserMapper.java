package com.nistagram.usermicroservice.user.controller.mapper;

import com.nistagram.usermicroservice.user.controller.dto.AgentDto;
import com.nistagram.usermicroservice.user.controller.dto.UserDto;
import com.nistagram.usermicroservice.user.controller.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setProfilePicPath(userDto.getProfilePicPath());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setWebSite(userDto.getWebSite());
        user.setBio(userDto.getBio());
        user.setBirthDate(userDto.getBirthDate());
        user.setUserGender(userDto.getUserGender());
        user.setPublicProfile(userDto.isPublicProfile());
        user.setPublicDM(userDto.isPublicDM());
        user.setTaggable(userDto.isTaggable());
        return user;
    }

    public static UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setProfilePicPath(user.getProfilePicPath());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setWebSite(user.getWebSite());
        userDto.setBio(user.getBio());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setUserGender(user.getUserGender());
        if (user.getVerificationRequest() != null)
            userDto.setStatus(user.getVerificationRequest().getStatus());
        userDto.setPublicProfile(user.isPublicProfile());
        userDto.setPublicDM(user.isPublicDM());
        userDto.setTaggable(user.isTaggable());
        return userDto;
    }

    public static List<UserDto> mapUserListToUserDtoList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : users)
            userDtos.add(mapUserToUserDto(u));
        return userDtos;
    }

    public static User mapUserRegistrationDtoToUser(UserRegistrationDto regDto) {
        User user = new User();
        user.setUsername(regDto.getUsername());
        user.setFullName(regDto.getFullName());
        user.setEmail(regDto.getEmail());
        return user;
    }

    public static User mapAgentDtoToUser(AgentDto regDto) {
        User user = new User();
        user.setUsername(regDto.getUsername());
        user.setFullName(regDto.getFullName());
        user.setEmail(regDto.getEmail());
        user.setWebSite(regDto.getWebSite());
        user.setPhone(regDto.getPhone());
        return user;
    }
}
