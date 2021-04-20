package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.repository.IUserRepository;
import com.xws.nistagrammonolith.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;


}
