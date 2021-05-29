package com.nistagram.authenticationMicroservice.repoistory;

import com.nistagram.authenticationMicroservice.domain.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlackListRepository extends JpaRepository<BlackList, Long> {

    List<BlackList> findAll();
}
