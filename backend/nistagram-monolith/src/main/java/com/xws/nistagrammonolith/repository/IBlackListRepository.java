package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBlackListRepository extends JpaRepository<BlackList, Long> {

    List<BlackList> findAll();
}
