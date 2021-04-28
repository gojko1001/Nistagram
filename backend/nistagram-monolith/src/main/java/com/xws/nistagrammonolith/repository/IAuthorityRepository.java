package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
