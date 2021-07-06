package com.nistagram.messengermicroservice.service.interfaces;

import com.nistagram.messengermicroservice.domain.Message;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;
import java.util.List;

@Service
public interface IMessageService {
    List<Message> getAll();
    List<Message> findAllByUsername(String username);
    Message createMessage(Message message);
    Message viewedMedia(Long id);
}
