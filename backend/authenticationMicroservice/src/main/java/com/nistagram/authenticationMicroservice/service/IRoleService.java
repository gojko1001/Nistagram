package com.nistagram.authenticationMicroservice.service;

import com.nistagram.authenticationMicroservice.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findById(Long id);

    List<Role> findByName(String name);
}
