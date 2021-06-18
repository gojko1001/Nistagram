package com.nistagram.usermicroservice.verify_account.service;

import com.nistagram.usermicroservice.verify_account.domain.UserCategory;
import com.nistagram.usermicroservice.verify_account.repository.UserCategoryRepository;
import com.nistagram.usermicroservice.verify_account.service.interfaces.IUserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryService implements IUserCategoryService {
    @Autowired
    private UserCategoryRepository userCategoryRepository;

    public List<UserCategory> getAll() {
        return userCategoryRepository.findAll();
    }
}
