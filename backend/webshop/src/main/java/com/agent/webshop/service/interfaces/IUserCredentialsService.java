package com.agent.webshop.service.interfaces;

import com.agent.webshop.domain.UserCredentials;
import org.springframework.http.ResponseEntity;

public interface IUserCredentialsService {
    UserCredentials save(UserCredentials userCredentials);
    ResponseEntity login(UserCredentials userCredentials);
}
