package com.nistagram.authenticationmicroservice.controller;

import com.nistagram.authenticationmicroservice.dto.AgentRequestDto;
import com.nistagram.authenticationmicroservice.service.IAgentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agentRequest")
@CrossOrigin(origins = "http://localhost:3000")
public class AgentRequestController {

    @Autowired
    private IAgentRequestService agentRequestService;

    @PostMapping("/add")
    public void sendRequest(@RequestBody AgentRequestDto agentRequestDto) {
        agentRequestService.create(agentRequestDto);
    }
    @GetMapping
    public List<AgentRequestDto> findAll(){
        return agentRequestService.findAll();
    }

    @PostMapping("/confirm/{id}")
    public void confirm(@PathVariable Long id) {
        agentRequestService.confirmRequest(id);
    }
    @PostMapping("/reject/{id}")
    public void reject(@PathVariable Long id) {
        agentRequestService.rejectRequest(id);
    }
}
