package com.agent.webshop.repository;


import com.agent.webshop.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findUserCredentialsByUsername(String username);
}
