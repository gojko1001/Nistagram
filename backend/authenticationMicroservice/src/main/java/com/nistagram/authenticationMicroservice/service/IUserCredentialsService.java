package com.nistagram.authenticationMicroservice.service;

import com.nistagram.authenticationMicroservice.domain.UserCredentials;
import com.nistagram.authenticationMicroservice.dto.LoginGoogleDto;
import com.nistagram.authenticationMicroservice.dto.ResetPasswordDto;
import com.nistagram.authenticationMicroservice.dto.UserCredentialsDto;

import java.io.IOException;

public interface IUserCredentialsService {

    UserCredentials login(UserCredentialsDto userCredentialsDto) throws IOException;

    UserCredentials loginGoogle(LoginGoogleDto loginGoogleDto) throws IOException;

    UserCredentials findByUsername(String username);

    void restartPassword(String jwt, ResetPasswordDto resetPasswordDto) throws IOException;

    void sendResetPasswordLink(String email) throws IOException;

    String verifyAccount(String username);
}
