package com.nistagram.authenticationmicroservice.repoistory;

import com.nistagram.authenticationmicroservice.domain.AgentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AgentRequestRepository extends JpaRepository<AgentRequest, Long> {

    AgentRequest save (AgentRequest agentRequest);

    List<AgentRequest> findAll();

    AgentRequest findAgentRequestById(Long id);
}
