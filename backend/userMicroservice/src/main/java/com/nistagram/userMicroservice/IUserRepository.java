package com.nistagram.userMicroservice;

import com.nistagram.userMicroservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User save(User user);

    List<User> findAll();

    User findBy();

    User findByEmail(String email);

    User findByUsername(String username);

}
