package com.nistagram.usermicroservice.user.service.interfaces;

import com.nistagram.usermicroservice.saga.event.GetUsernameEvent;
import com.nistagram.usermicroservice.saga.event.UpdateUsernameEvent;
import com.nistagram.usermicroservice.user.controller.dto.AgentDto;
import com.nistagram.usermicroservice.user.controller.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public interface IUserService {

    List<User> getAll();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByFullName(String fullName);

    User registerUser(UserRegistrationDto userReg, boolean isGoogleUser);

    User registerAgent(AgentDto agentDto);

    User updateUser(User user, String oldUsername, String jwt);

    User save(User user);

    List<User> search(String username);

    List<String> arePublic(List<String> usernames);

    List<String> getPublicUsers();

    List<String> getPublicTaggableUsers();

    List<User> getUsersWithVerifyRequestPending();

    List<User> getChatableUsers(String username, String text);

    Boolean isPublic(String username);

    List<User> getChatUsers(String username);

    void cancelUpdateUsername(GetUsernameEvent event);

    void doneUpdateUsername(GetUsernameEvent event);
}
