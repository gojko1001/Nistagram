package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findById(Long id);

    List<Role> findByName(String name);
}