package com.nistagram.authenticationMicroservice.service;

import com.nistagram.authenticationMicroservice.domain.Role;
import com.nistagram.authenticationMicroservice.repoistory.IRoleRepository;
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
        Role role = roleRepository.findByName(name);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }
}
