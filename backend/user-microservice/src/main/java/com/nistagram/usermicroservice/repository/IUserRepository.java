package com.nistagram.usermicroservice.repository;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.verify_account.domain.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User save(User user);

    List<User> findAll();

    User findByEmail(String email);

    User findByUsername(String username);

    @Query(value = "select distinct u from User u where lower(u.username) like concat('%', lower(:username), '%' ) " +
            "or lower(u.fullName) like concat('%', lower(:username), '%' ) ")
    List<User> search(String username);

    List<User> findByVerificationRequest_Status(VerificationStatus status);

    User findByFullName(String fullName);

}
