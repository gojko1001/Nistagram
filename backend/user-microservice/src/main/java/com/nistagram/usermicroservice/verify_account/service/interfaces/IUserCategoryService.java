package com.nistagram.usermicroservice.verify_account.service.interfaces;

import com.nistagram.usermicroservice.verify_account.domain.UserCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserCategoryService {
    List<UserCategory> getAll();
}
