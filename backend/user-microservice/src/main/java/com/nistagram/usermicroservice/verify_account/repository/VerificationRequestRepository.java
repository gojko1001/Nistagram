package com.nistagram.usermicroservice.verify_account.repository;

import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRequestRepository extends JpaRepository<VerificationRequest, Long> { }
