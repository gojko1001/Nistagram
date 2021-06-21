package com.nistagram.authenticationmicroservice.repoistory;

import com.nistagram.authenticationmicroservice.domain.Role;
import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    UserCredentials save(UserCredentials userCredentials);

    UserCredentials findByUsername(String username);

    List<UserCredentials> findAll();

}
