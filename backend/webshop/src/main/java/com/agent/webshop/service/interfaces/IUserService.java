package com.agent.webshop.service.interfaces;

import com.agent.webshop.controller.dto.UserRegistrationDto;
import com.agent.webshop.domain.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity registerUser(UserRegistrationDto userReg);
    User findByUsername(String username);
}
