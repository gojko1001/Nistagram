package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.Role;
import com.xws.nistagrammonolith.repository.IRoleRepository;
import com.xws.nistagrammonolith.service.interfaces.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findById(Long id) {
        log.info("Try to get role with id: " + id);
        Role role = roleRepository.getOne(id);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public List<Role> findByName(String name) {
        log.info("Try to get role with name: " + name);
        Role role = roleRepository.findByName(name);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }
}
