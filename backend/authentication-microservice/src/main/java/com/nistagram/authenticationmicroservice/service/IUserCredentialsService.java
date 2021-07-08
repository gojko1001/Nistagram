package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationmicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationmicroservice.dto.UserCredentialsAgentDto;
import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationmicroservice.saga.event.GetUsernameEvent;
import com.nistagram.authenticationmicroservice.saga.event.UpdateUsernameEvent;

import java.io.IOException;
import java.util.List;

public interface IUserCredentialsService {

    UserCredentials findByUsername(String username);

    UserCredentials create(UserCredentialsDto userCredentials);

    UserCredentials createAgent(UserCredentialsAgentDto userCredentials);

    UserCredentials login(String username, String password) throws IOException;

    UserCredentials loginGoogle(LoginGoogleDto loginGoogleDto) throws IOException;

    List<String> findCredentialsByRoleUserAndAgent();

    void changeUsername(String newUsername, String jwt);

    void changePassword(ResetPasswordDto resetPasswordDto, String jwt);

    void restartPassword(String jwt, ResetPasswordDto resetPasswordDto) throws IOException;

    void sendResetPasswordLink(String email) throws IOException;

    void deactivateProfile(String username);

    String verifyAccount(String username);

    void newUpdateUsername(GetUsernameEvent event);

    void usernameUpdateDone(GetUsernameEvent event);

    void cancelUpdateUsername(GetUsernameEvent event);
}
