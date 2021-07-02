package com.agent.webshop.service;

import com.agent.webshop.domain.UserCredentials;
import com.agent.webshop.exception.BadRequestException;
import com.agent.webshop.exception.NotFoundException;
import com.agent.webshop.repository.IUserCredentialsRepository;
import com.agent.webshop.service.interfaces.IRoleService;
import com.agent.webshop.service.interfaces.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService implements IUserCredentialsService {
    @Autowired
    private IUserCredentialsRepository userCredentialsRepository;
    @Autowired
    private IRoleService roleService;

    @Override
    public UserCredentials save(UserCredentials userCredentials){
        return userCredentialsRepository.save(userCredentials);
    }

    @Override
    public UserCredentials login(UserCredentials userCredentials){
        UserCredentials userCredentialsDb = userCredentialsRepository.findByUsername(userCredentials.getUsername());
        if(!userCredentialsDb.getPassword().equals(userCredentials.getPassword()) || userCredentials == null)
            throw new BadRequestException("Username or password is not correct.");
        return userCredentialsDb;
    }

    @Override
    public UserCredentials findByUsername(String username) {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
        if (userCredentials == null) {
            throw new NotFoundException("There is no user credentials with username " + username);
        }
        return userCredentials;
    }
}
