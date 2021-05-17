package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    Image save(Image image);
    List<Image> findAll();
    List<Image> findImagesByUsername(String username);
    Image findImageById(Long id);
}
