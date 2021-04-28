package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlackListRepository extends JpaRepository<BlackList, Long> {

    List<BlackList> findAll();
}
