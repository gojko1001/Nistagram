package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.UserCredentials;
import com.xws.nistagrammonolith.controller.dto.UserCredentialsDto;

import java.io.IOException;

public interface IUserCredentialsService {

    UserCredentials login(UserCredentialsDto userCredentialsDto) throws IOException;

    void restartPassword(String username) throws IOException;
}
