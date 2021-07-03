package com.agent.webshop.service;

import com.agent.webshop.controller.dto.UserRegistrationDto;
import com.agent.webshop.controller.mapper.UserMapper;
import com.agent.webshop.domain.User;
import com.agent.webshop.domain.UserCredentials;
import com.agent.webshop.repository.IUserRepository;
import com.agent.webshop.service.interfaces.IRoleService;
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
    @Autowired
    private IRoleService roleService;

    @Override
    public ResponseEntity registerUser(UserRegistrationDto userReg) {
        if(userRepository.findByUsername(userReg.getUsername()) != null){
            return new ResponseEntity("User with that username already exist.", HttpStatus.BAD_REQUEST);
        }
        UserCredentials userCredentials = UserMapper.mapUserRegistrationDtoToUserCredentials(userReg);
        userCredentials.setRole(roleService.findRoleByName(userReg.getRole()));
        userCredentialsService.save(userCredentials);
        User user = UserMapper.mapUserRegistrationDtoToUser(userReg);
        userRepository.save(user);
        return new ResponseEntity("User has registered successfully.", HttpStatus.OK);
    }

}
