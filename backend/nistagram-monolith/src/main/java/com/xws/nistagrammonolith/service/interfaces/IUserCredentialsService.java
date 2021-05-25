package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.LoginGoogleDto;
import com.xws.nistagrammonolith.controller.dto.ResetPasswordDto;
import com.xws.nistagrammonolith.controller.dto.UserCredentialsDto;
import com.xws.nistagrammonolith.domain.UserCredentials;

import java.io.IOException;

public interface IUserCredentialsService {

    UserCredentials login(UserCredentialsDto userCredentialsDto) throws IOException;

    UserCredentials loginGoogle(LoginGoogleDto loginGoogleDto) throws IOException;

    UserCredentials findByUsername(String username);

    void restartPassword(String jwt, ResetPasswordDto resetPasswordDto) throws IOException;

    void sendResetPasswordLink(String email) throws IOException;


}
