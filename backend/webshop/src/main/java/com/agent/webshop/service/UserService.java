package com.agent.webshop.service;

import com.agent.webshop.controller.dto.UserRegistrationDto;
import com.agent.webshop.controller.mapper.UserMapper;
import com.agent.webshop.domain.User;
import com.agent.webshop.repository.IUserRepository;
import com.agent.webshop.service.interfaces.IUserCredentialsService;
import com.agent.webshop.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserCredentialsService userCredentialsService;

    @Override
    public ResponseEntity registerUser(UserRegistrationDto userReg) {
        userCredentialsService.save(UserMapper.mapUserRegistrationDtoToUserCredentials(userReg));
        User user = UserMapper.mapUserRegistrationDtoToUser(userReg);
        userRepository.save(user);
        return new ResponseEntity("User has registered successfully.", HttpStatus.OK);
    }

}
