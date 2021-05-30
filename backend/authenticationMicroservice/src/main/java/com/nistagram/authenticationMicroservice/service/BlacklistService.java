package com.nistagram.authenticationMicroservice.service;

import com.nistagram.authenticationMicroservice.domain.BlackList;
import com.nistagram.authenticationMicroservice.exception.InvalidActionException;
import com.nistagram.authenticationMicroservice.repoistory.IBlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistService implements IBlacklistService{
    @Autowired
    private IBlackListRepository blackListRepository;

    @Override
    public Boolean isBlacklisted(String password) {
        for (BlackList b : blackListRepository.findAll()) {
            if (b.getPassword().equalsIgnoreCase(password)) {
                throw new InvalidActionException("You can't use this password, it is in top 20 most common passwords");
            }
        }
        return true;
    }
}
