package com.xws.nistagrammonolith.repository;

import com.xws.nistagrammonolith.domain.media.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFavouriteRepository extends JpaRepository<Favourite, Long> {
}