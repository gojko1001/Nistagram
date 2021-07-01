package com.agent.webshop.service;

import com.agent.webshop.domain.UserCredentials;
import com.agent.webshop.repository.IUserCredentialsRepository;
import com.agent.webshop.service.interfaces.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService implements IUserCredentialsService {
    @Autowired
    private IUserCredentialsRepository userCredentialsRepository;

    @Override
    public UserCredentials save(UserCredentials userCredentials){
        return userCredentialsRepository.save(userCredentials);
    }

    @Override
    public ResponseEntity login(UserCredentials userCredentials){
        UserCredentials userCredentialsDb = userCredentialsRepository.findUserCredentialsByUsername(userCredentials.getUsername());
        if(!userCredentialsDb.getPassword().equals(userCredentials.getPassword()) || userCredentials == null)
            return new ResponseEntity("Username or password is not correct.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity("User logged in.", HttpStatus.OK);
    }
}
