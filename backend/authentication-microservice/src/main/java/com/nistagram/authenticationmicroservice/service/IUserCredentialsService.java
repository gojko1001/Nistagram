package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationmicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;

import java.io.IOException;

public interface IUserCredentialsService {

    UserCredentials findByUsername(String username);

    UserCredentials create(UserCredentialsDto userCredentials);

    UserCredentials login(String username, String password) throws IOException;

    UserCredentials loginGoogle(LoginGoogleDto loginGoogleDto) throws IOException;

    void changeUsername(String oldUsername, String newUsername);

    void changePassword(ResetPasswordDto resetPasswordDto, String jwt);

    void restartPassword(String jwt, ResetPasswordDto resetPasswordDto) throws IOException;

    void sendResetPasswordLink(String email) throws IOException;

    String verifyAccount(String username);
}
