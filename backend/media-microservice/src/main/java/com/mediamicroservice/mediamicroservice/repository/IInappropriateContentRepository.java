package com.mediamicroservice.mediamicroservice.repository;

import com.mediamicroservice.mediamicroservice.domain.InappropriateContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IInappropriateContentRepository extends JpaRepository<InappropriateContent, Long> {

    InappropriateContent save(InappropriateContent inappropriateContent);

    List<InappropriateContent> findAll();

}
