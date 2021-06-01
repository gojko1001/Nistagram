package com.nistagram.authenticationmicroservice.repoistory;

import com.nistagram.authenticationmicroservice.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
