package com.nistagram.authenticationMicroservice.service;

import com.nistagram.authenticationMicroservice.domain.UserCredentials;
import com.nistagram.authenticationMicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationMicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationMicroservice.dto.UserCredentialsDto;

import java.io.IOException;

public interface IUserCredentialsService {

    UserCredentials findByUsername(String username);

    UserCredentials create(UserCredentials userCredentials);

    UserCredentials login(UserCredentialsDto userCredentialsDto) throws IOException;

    UserCredentials loginGoogle(LoginGoogleDto loginGoogleDto) throws IOException;

    void restartPassword(String jwt, ResetPasswordDto resetPasswordDto) throws IOException;

    void sendResetPasswordLink(String email) throws IOException;

    String verifyAccount(String username);
}
