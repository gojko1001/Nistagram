package com.nistagram.authenticationMicroservice.repoistory;

import com.nistagram.authenticationMicroservice.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
