package com.nistagram.messengermicroservice.service;

import com.nistagram.messengermicroservice.domain.Message;
import com.nistagram.messengermicroservice.saga.event.GetUsernameEvent;
import com.nistagram.messengermicroservice.saga.event.MessageFactory;
import com.nistagram.messengermicroservice.saga.eventListener.CancelUpdateListener;
import com.nistagram.messengermicroservice.saga.eventListener.UsernameUpdateDoneListener;
import com.nistagram.messengermicroservice.service.interfaces.IMessageService;
import com.nistagram.messengermicroservice.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IMessageService messageService;
    private final CancelUpdateListener cancelUpdateListener;
    private final UsernameUpdateDoneListener updateDoneListener;

    @Transactional
    public void changeUsername(String newUsername, String oldUsername){
        List<Message> messages = messageService.findAllByUsername(oldUsername);
        if(!messages.isEmpty()){
            messages.stream().forEach(message -> {
                if(message.getSender().equals(oldUsername))
                    message.setSender(newUsername);
                else if(message.getReceiver().equals(oldUsername))
                    message.setReceiver(newUsername);
                messageService.save(message);
            });
        }
    }

    @Override
    @Transactional
    public void cancelUpdateUsername(GetUsernameEvent event) {
        List<Message> messages = messageService.findAllByUsername(event.getNewUsername());
        if(!messages.isEmpty()){
            messages.stream().forEach(message -> {
                if(message.getSender().equals(event.getNewUsername()))
                    message.setSender(event.getOldUsername());
                else if(message.getReceiver().equals(event.getNewUsername()))
                    message.setReceiver(event.getOldUsername());
                messageService.save(message);
            });
        }
    }

    @Override
    @Transactional
    public void updateUsername(GetUsernameEvent event) {
        try {
            changeUsername(event.getNewUsername(), event.getOldUsername());
            updateDoneListener.sendMessageToNewUpdate(
                    MessageFactory.createUpdateUsernameMessage(event.getNewUsername(), event.getOldUsername()));
        }catch (Exception e){
            e.printStackTrace();
            cancelUpdateListener.sendMessageToNewUpdate(
                    MessageFactory.createUpdateUsernameMessage(event.getNewUsername(), event.getOldUsername())
            );
        }
    }
}
