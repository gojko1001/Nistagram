package com.nistagram.messengermicroservice.controller.mapping;

import com.nistagram.messengermicroservice.controller.dto.CreateMessageDto;
import com.nistagram.messengermicroservice.controller.dto.ImageByteDto;
import com.nistagram.messengermicroservice.controller.dto.MessageDto;
import com.nistagram.messengermicroservice.domain.MediaName;
import com.nistagram.messengermicroservice.domain.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageMapper {

    public static Message mapCreateMessageDtoToMessage(CreateMessageDto messageDto) {
        List<MediaName> mediaNames = new ArrayList<MediaName>();
        Message message = new Message();

        message.setContent(messageDto.getContent());
        message.setSender(messageDto.getSender());
        message.setReceiver(messageDto.getReceiver());
        message.setDate(new Date());

        if (messageDto.getFileNames().isEmpty()) {
            message.setHasMedia(false);
        } else {
            message.setHasMedia(Boolean.TRUE);
            message.setViewed(Boolean.FALSE);
            for (String fileName : messageDto.getFileNames()) {
                MediaName mediaName = new MediaName();
                mediaName.setFileName(fileName);
                if (fileName.contains(".mp4"))
                    mediaName.setIsImage(Boolean.FALSE);
                mediaNames.add(mediaName);
            }
            message.setMediaName(mediaNames);
        }
        return message;
    }

    public static MessageDto mapMessageToMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setContent(message.getContent());
        messageDto.setSender(message.getSender());
        messageDto.setReceiver(message.getReceiver());
        messageDto.setViewed(message.getViewed());
        messageDto.setImageBytes(new ArrayList<ImageByteDto>());
        messageDto.setHasMedia(message.getHasMedia());
        messageDto.setNeedRequest(message.getNeedRequest());
        return messageDto;
    }
}
