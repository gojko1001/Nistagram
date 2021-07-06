package com.nistagram.messengermicroservice.controller;

import com.nistagram.messengermicroservice.controller.dto.CreateMessageDto;
import com.nistagram.messengermicroservice.controller.mapping.MessageMapper;
import com.nistagram.messengermicroservice.service.interfaces.IMessageService;
import com.nistagram.messengermicroservice.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MessengerController {

    private static final String SENDING_URL = "/topic/server-message";
    private static String uploadDir = "message-photos";

    //    private final SimpMessagingTemplate template;
    private final IMessageService messageService;

//    @Scheduled(fixedRate = 1000)
//    public void getMessages(@PathVariable String username) {
//        List<Message> messages = messageService.findAllByUsername(username);
//        template.convertAndSend(SENDING_URL, messages);
//    }

    @GetMapping("/{username}")
    public ResponseEntity getAllByUsername(@PathVariable String username) {
        return new ResponseEntity(
                messageService.getImagesFiles(messageService.findAllByUsername(username)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createMessage(@RequestBody CreateMessageDto messageDto) {
        return new ResponseEntity(messageService.createMessage(MessageMapper.mapCreateMessageDtoToMessage(messageDto)), HttpStatus.OK);
    }

    @PostMapping("/image")
    public List<String> saveImage(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNames = new ArrayList<String>();
        for (MultipartFile mf : multipartFiles) {
            String fileName = StringUtils.cleanPath(mf.getOriginalFilename().replaceAll("\\s", ""));
            uploadDir = "message-photos";
            FileUploadUtil.saveFile(uploadDir, fileName, mf);
            fileNames.add(fileName);
        }
        return fileNames;
    }

    @PutMapping("/{id}")
    public ResponseEntity viewedMedia(@PathVariable Long id) {
        return new ResponseEntity(messageService.viewedMedia(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/request/{id}")
    public ResponseEntity request(@PathVariable Long id){
        return new ResponseEntity(messageService.changeRequest(id), HttpStatus.OK);
    }
}
