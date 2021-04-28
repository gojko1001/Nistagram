package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.Authority;

import java.util.List;

public interface IAuthorityService {
    List<Authority> findById(Long id);
    List<Authority> findByName(String name);
}
