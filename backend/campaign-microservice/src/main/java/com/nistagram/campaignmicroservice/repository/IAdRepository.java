package com.nistagram.campaignmicroservice.repository;

import com.nistagram.campaignmicroservice.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdRepository extends JpaRepository<Ad, Long> {
}
