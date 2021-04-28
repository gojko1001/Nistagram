package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Role;
import com.xws.nistagrammonolith.repository.IAuthorityRepository;
import com.xws.nistagrammonolith.service.interfaces.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService implements IAuthorityService {
    @Autowired
    private IAuthorityRepository authorityRepository;

    @Override
    public List<Role> findById(Long id) {
        Role auth = authorityRepository.getOne(id);
        List<Role> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public List<Role> findByName(String name) {
        Role auth = authorityRepository.findByName(name);
        List<Role> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }
}
