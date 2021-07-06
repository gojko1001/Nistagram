package com.nistagram.messengermicroservice.service;

import com.nistagram.messengermicroservice.domain.MediaName;
import com.nistagram.messengermicroservice.domain.Message;
import com.nistagram.messengermicroservice.repository.MessageRepository;
import com.nistagram.messengermicroservice.service.interfaces.IMediaNameService;
import com.nistagram.messengermicroservice.service.interfaces.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final IMediaNameService mediaNameService;

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findAllByUsername(String username) {
        return messageRepository.findAllByUsername(username);
    }

    public Message createMessage(Message message){
        if(message.getHasMedia() && !message.getMediaName().isEmpty() && message.getMediaName()!=null){
            for(MediaName mediaName: message.getMediaName())
                mediaNameService.create(mediaName);
        }
        return messageRepository.save(message);
    }

    @Override
    public Message viewedMedia(Long id) {
        Message message = messageRepository.findMessageById(id);
        message.setViewed(Boolean.TRUE);
        return messageRepository.save(message);
    }
}
