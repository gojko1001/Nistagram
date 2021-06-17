package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateInappropriateContentDto;
import com.mediamicroservice.mediamicroservice.domain.InappropriateContent;
import com.mediamicroservice.mediamicroservice.service.interfaces.IInappropriateContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inappropriate")
public class InappropriateContentController {

    @Autowired
    private IInappropriateContentService inappropriateContentService;

    @PostMapping
    public InappropriateContent createCommentOnPost(@RequestBody CreateInappropriateContentDto createInappropriateContentDto) {
        return inappropriateContentService.create(createInappropriateContentDto);
    }
}
