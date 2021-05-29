package com.nistagram.authenticationMicroservice.repoistory;

import com.nistagram.authenticationMicroservice.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    UserCredentials save(UserCredentials userCredentials);

    UserCredentials findByUsername(String username);
}
