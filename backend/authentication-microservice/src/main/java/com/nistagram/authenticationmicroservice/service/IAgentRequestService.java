package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.domain.AgentRequest;
import com.nistagram.authenticationmicroservice.dto.AgentRequestDto;

import java.util.List;

public interface IAgentRequestService {

    AgentRequest create(AgentRequestDto agentRequest);

    List<AgentRequestDto> findAll();
}
