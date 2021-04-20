package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.repository.IUserCredentialsRepository;
import com.xws.nistagrammonolith.service.interfaces.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService implements IUserCredentialsService {
    @Autowired
    private IUserCredentialsRepository userCredentialsRepository;
}
