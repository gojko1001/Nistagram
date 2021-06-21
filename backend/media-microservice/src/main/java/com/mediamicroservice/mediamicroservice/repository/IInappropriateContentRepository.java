package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.InappropriateContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInappropriateContentRepository extends JpaRepository<InappropriateContent, Long> {

    InappropriateContent save(InappropriateContent inappropriateContent);

    List<InappropriateContent> findAll();

    List<InappropriateContent> findInappropriateContentsByRequestedBy(String username);

    List<InappropriateContent> findInappropriateContentsByRespondedBy(String username);

}
