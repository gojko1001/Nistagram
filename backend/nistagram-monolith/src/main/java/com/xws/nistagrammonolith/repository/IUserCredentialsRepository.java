package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    UserCredentials save(UserCredentials userCredentials);

    UserCredentials findByUsername(String username);
}
