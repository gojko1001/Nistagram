package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.domain.AgentRequest;
import com.nistagram.authenticationmicroservice.dto.AgentRequestDto;
import com.nistagram.authenticationmicroservice.repoistory.AgentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AgentRequestService implements IAgentRequestService{
    @Autowired
    private AgentRequestRepository agentRequestRepository;

    @Override
    public AgentRequest create(AgentRequestDto agentRequest) {
        AgentRequest agentRequest1 = new AgentRequest();
        agentRequest1.setEmail(agentRequest.getEmail());
        agentRequest1.setUsername(agentRequest.getUsername());
        agentRequest1.setWebSite(agentRequest.getWebSite());
        return agentRequestRepository.save(agentRequest1);
    }

    @Override
    public List<AgentRequestDto> findAll() {
        List<AgentRequest> agentRequests = agentRequestRepository.findAll();
        List<AgentRequestDto> newList = new ArrayList<>();
        for(AgentRequest ar : agentRequests){
            if(ar.getIsConfirmed().equals(false)&&ar.getIsRejected().equals(false)){
                AgentRequestDto agentRequestDto = new AgentRequestDto();
                agentRequestDto.setEmail(ar.getEmail());
                agentRequestDto.setUsername(ar.getUsername());
                agentRequestDto.setWebSite(ar.getWebSite());
                agentRequestDto.setId(ar.getId());
                newList.add(agentRequestDto);
            }
        }
        return newList;
    }
}
