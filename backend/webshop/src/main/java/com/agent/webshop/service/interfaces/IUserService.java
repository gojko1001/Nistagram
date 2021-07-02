package com.agent.webshop.service.interfaces;

import com.agent.webshop.controller.dto.UserRegistrationDto;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity registerUser(UserRegistrationDto userReg);
}
