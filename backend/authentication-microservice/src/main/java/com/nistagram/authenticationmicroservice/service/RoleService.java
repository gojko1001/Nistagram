package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.domain.Role;
import com.nistagram.authenticationmicroservice.logger.Logger;
import com.nistagram.authenticationmicroservice.repoistory.IRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findById(Long id) {
        Role role = roleRepository.getOne(id);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public List<Role> findByName(String name) {
        Logger.infoDb("Try to find role: " + name);
        Role role = roleRepository.findByName(name);
        if(role == null)
            Logger.infoDb("There is no role: " + name);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }
}
