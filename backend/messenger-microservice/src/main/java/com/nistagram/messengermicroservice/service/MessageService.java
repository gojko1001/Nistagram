package com.nistagram.messengermicroservice.service;

import com.nistagram.messengermicroservice.domain.Message;
import com.nistagram.messengermicroservice.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService{

    private final MessageRepository messageRepository;

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findAllByUsername(String username) {
        return messageRepository.findAllByUsername(username);
    }
}
