package com.nistagram.messengermicroservice.repository;

import com.nistagram.messengermicroservice.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select m from Message m where m.receiver=:username or m.sender=:username")
    List<Message> findAllByUsername(@Param("username")String username);
}
