package com.agent.webshop.service;

import com.agent.webshop.domain.Role;
import com.agent.webshop.repository.IRoleRepository;
import com.agent.webshop.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }
}
