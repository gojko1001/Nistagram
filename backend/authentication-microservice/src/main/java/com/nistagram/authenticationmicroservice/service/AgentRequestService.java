package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.connection.UserConnection;
import com.nistagram.authenticationmicroservice.domain.AgentRequest;
import com.nistagram.authenticationmicroservice.domain.Role;
import com.nistagram.authenticationmicroservice.domain.UserCredentials;
import com.nistagram.authenticationmicroservice.dto.AgentRequestDto;
import com.nistagram.authenticationmicroservice.dto.UserDto;
import com.nistagram.authenticationmicroservice.repoistory.AgentRequestRepository;
import com.nistagram.authenticationmicroservice.repoistory.IRoleRepository;
import com.nistagram.authenticationmicroservice.repoistory.IUserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class AgentRequestService implements IAgentRequestService{
    @Autowired
    private AgentRequestRepository agentRequestRepository;
    @Autowired
    private UserConnection userConnection;
    @Autowired
    private IUserCredentialsService userCredentialsService;
    @Autowired
    private IRoleRepository roleService;
    @Autowired
    private IUserCredentialsRepository userCredentialsRepository;

    @Override
    public AgentRequest create(AgentRequestDto agentRequest) {
        AgentRequest agentRequest1 = new AgentRequest();
        UserDto userDto = userConnection.getUserByUsername(agentRequest.getUsername());
        agentRequest1.setEmail(userDto.getEmail());
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

    @Override
    public void confirmRequest(Long id) {
        AgentRequest agentRequest = agentRequestRepository.findAgentRequestById(id);
        agentRequest.setIsConfirmed(true);
        agentRequestRepository.save(agentRequest);
        UserCredentials userCredentials = userCredentialsService.findByUsername(agentRequest.getUsername());
        Collection<Role> roles = userCredentials.getRoles();
        Role role = roleService.findByName("ROLE_AGENT");
        roles.add(role);
        userCredentialsRepository.save(userCredentials);


    }

    @Override
    public void rejectRequest(Long id) {
        AgentRequest agentRequest = agentRequestRepository.findAgentRequestById(id);
        agentRequest.setIsRejected(true);
        agentRequestRepository.save(agentRequest);
    }
}
