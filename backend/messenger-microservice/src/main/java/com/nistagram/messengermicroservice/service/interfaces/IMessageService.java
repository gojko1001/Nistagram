package com.nistagram.messengermicroservice.service.interfaces;

import com.nistagram.messengermicroservice.controller.dto.MessageDto;
import com.nistagram.messengermicroservice.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMessageService {
    List<Message> getAll();

    List<Message> findAllByUsername(String username);

    Message createMessage(Message message);

    Message viewedMedia(Long id);

    List<MessageDto> getImagesFiles(List<Message> messages);

    MessageDto imageFile(Message message, String filePath);

    void deleteMessage(Long id);

    Message changeRequest(Long id);
}
