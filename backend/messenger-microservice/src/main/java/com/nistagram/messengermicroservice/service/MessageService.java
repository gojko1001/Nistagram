package com.nistagram.messengermicroservice.service;

import com.nistagram.messengermicroservice.connection.PostConnection;
import com.nistagram.messengermicroservice.connection.UserConnection;
import com.nistagram.messengermicroservice.controller.dto.ImageByteDto;
import com.nistagram.messengermicroservice.controller.dto.MessageDto;
import com.nistagram.messengermicroservice.controller.mapping.MessageMapper;
import com.nistagram.messengermicroservice.domain.MediaName;
import com.nistagram.messengermicroservice.domain.Message;
import com.nistagram.messengermicroservice.repository.MessageRepository;
import com.nistagram.messengermicroservice.service.interfaces.IMediaNameService;
import com.nistagram.messengermicroservice.service.interfaces.IMessageService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final IMediaNameService mediaNameService;
    private final PostConnection postConnection;
    private final UserConnection userConnection;

    private static final String uploadDir = "message-photos";
    private static final String link = "http://localhost:3000/comment";

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findAllByUsername(String username) {
        return messageRepository.findAllByUsername(username);
    }

    public Message createMessage(Message message) {
        message = needRequest(message);
        if (message.getHasMedia() && !message.getMediaName().isEmpty() && message.getMediaName() != null) {
            for (MediaName mediaName : message.getMediaName())
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

    public List<MessageDto> getImagesFiles(List<Message> messages) {
        List<MessageDto> messageDtos = new ArrayList<MessageDto>();
        if (messages != null && !messages.isEmpty()) {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/src/main/resources/" + uploadDir + "/");
            for (Message message : messages) {
                messageDtos.add(imageFile(message, filePath));
            }
        }
        return messageDtos;
    }

    public MessageDto imageFile(Message message, String filePath) {
        MessageDto messageDto = MessageMapper.mapMessageToMessageDto(message);
        messageDto = getLink(message, messageDto);
        for (MediaName mediaName : message.getMediaName()) {
            File in = new File(filePath + mediaName.getFileName());
            ImageByteDto imageByteDto = new ImageByteDto();
            try {
                imageByteDto.setImageByte(IOUtils.toByteArray(new FileInputStream(in)));
                imageByteDto.setIsImage(mediaName.getIsImage());
                messageDto.getImageBytes().add(imageByteDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return messageDto;
    }

    @Override
    public void deleteMessage(Long id) {
        Message message = messageRepository.findMessageById(id);
        messageRepository.delete(message);
    }

    @Override
    public Message changeRequest(Long id) {
        Message message = messageRepository.findMessageById(id);
        message.setNeedRequest(Boolean.FALSE);
        return messageRepository.save(message);
    }

    private MessageDto getLink(Message message, MessageDto messageDto) {
        if (message.getContent().contains(" ")) {
            String[] results = message.getContent().split(" ");
            for (String res : results) {
                if (res.contains(link)) {
                    messageDto.setLink(res);
                    Long postId = Long.parseLong(res.split("/")[4]);
                    String username = postConnection.getUsernameById(postId);
                    messageDto.setPrivateLink(userConnection.isPublic(username));
                }
            }
        } else if (message.getContent().contains(link)) {
            messageDto.setLink(message.getContent());
            Long postId = Long.parseLong(message.getContent().split("/")[4]);
            String username = postConnection.getUsernameById(postId);
            messageDto.setPrivateLink(userConnection.isPublic(username));
        }
        return messageDto;
    }

    private Message needRequest(Message message){
        List<Message> messages = messageRepository.findAll();
        for(Message m: messages){
            if(m.getReceiver().equals(message.getReceiver()) && m.getSender().equals(message.getSender())) {
                message.setNeedRequest(Boolean.FALSE);
                return message;
            }
        }
        boolean isFollowing = userConnection.isFollow(message.getReceiver(), message.getSender());
        if(Boolean.TRUE.equals(isFollowing))
            message.setNeedRequest(Boolean.FALSE);
        else
            message.setNeedRequest(Boolean.TRUE);
        return message;
    }
}
