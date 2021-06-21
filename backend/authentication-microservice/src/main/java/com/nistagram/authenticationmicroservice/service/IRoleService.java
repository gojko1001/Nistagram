package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findById(Long id);

    List<Role> findByName(String name);

    boolean isAdmin(String username);
}
