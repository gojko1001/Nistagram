package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.domain.Role;
import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.logger.Logger;
import com.nistagram.authenticationmicroservice.repoistory.IRoleRepository;
import com.nistagram.authenticationmicroservice.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserCredentialsService userCredentialsService;

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

    @Override
    public boolean isAdmin(String username) {
        boolean isAdmin=true;
        UserCredentials userCredentials = userCredentialsService.findByUsername(username);
        Collection<Role> roles = userCredentials.getRoles();
        for(Role r:roles){
            if(r.getName().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
            else
                isAdmin = false;
        }
        return isAdmin;
    }
}
