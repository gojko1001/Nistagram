package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Role;
import com.xws.nistagrammonolith.repository.IRoleRepository;
import com.xws.nistagrammonolith.service.interfaces.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IAuthorityService {
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
