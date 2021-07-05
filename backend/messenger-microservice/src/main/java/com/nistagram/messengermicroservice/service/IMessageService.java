package com.nistagram.messengermicroservice.service;

import com.nistagram.messengermicroservice.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMessageService {
    List<Message> getAll();
    List<Message> findAllByUsername(String username);
}
