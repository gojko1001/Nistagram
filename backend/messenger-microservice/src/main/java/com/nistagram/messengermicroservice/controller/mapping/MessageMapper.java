package com.nistagram.messengermicroservice.controller.mapping;

import com.nistagram.messengermicroservice.controller.dto.CreateMessageDto;
import com.nistagram.messengermicroservice.controller.dto.ImageByteDto;
import com.nistagram.messengermicroservice.controller.dto.MessageDto;
import com.nistagram.messengermicroservice.domain.MediaName;
import com.nistagram.messengermicroservice.domain.Message;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageMapper {

    private static String uploadDir = "message-photos";

    public static List<MessageDto> getImagesFiles(List<Message> messages){
        List<MessageDto> messageDtos = new ArrayList<>();
        if(messages != null && !messages.isEmpty()){
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/src/main/resources/" + uploadDir + "/");
            for (Message message : messages) {
                messageDtos.add(imageFile(message, filePath));
            }
        }
        return messageDtos;
    }

    public static Message mapCreateMessageDtoToMessage(CreateMessageDto messageDto){
        List<MediaName> mediaNames = new ArrayList<>();
        Message message = new Message();

        message.setContent(messageDto.getContent());
        message.setSender(messageDto.getSender());
        message.setReceiver(messageDto.getReceiver());
        message.setDate(new Date());

        if(messageDto.getFileNames().isEmpty()){
            message.setHasMedia(false);
        }else{
            message.setHasMedia(Boolean.TRUE);
            message.setViewed(Boolean.FALSE);
            for(String fileName: messageDto.getFileNames()){
                MediaName mediaName = new MediaName();
                mediaName.setFileName(fileName);
                if(fileName.contains(".mp4"))
                    mediaName.setIsImage(Boolean.FALSE);
                mediaNames.add(mediaName);
            }
            message.setMediaName(mediaNames);
        }
        return message;
    }

    private static MessageDto mapMessageToMessageDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setContent(message.getContent());
        messageDto.setSender(message.getSender());
        messageDto.setReceiver(message.getReceiver());
        messageDto.setViewed(message.getViewed());
        messageDto.setImageBytes(new ArrayList<>());
        messageDto.setHasMedia(message.getHasMedia());
        return messageDto;
    }

    private static MessageDto imageFile(Message message, String filePath){
        MessageDto messageDto = mapMessageToMessageDto(message);
        for(MediaName mediaName: message.getMediaName()){
            File in = new File(filePath + mediaName.getFileName());
            ImageByteDto imageByteDto = new ImageByteDto();
            try{
                imageByteDto.setImageByte(IOUtils.toByteArray(new FileInputStream(in)));
                imageByteDto.setIsImage(mediaName.getIsImage());
                messageDto.getImageBytes().add(imageByteDto);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return messageDto;
    }
}
