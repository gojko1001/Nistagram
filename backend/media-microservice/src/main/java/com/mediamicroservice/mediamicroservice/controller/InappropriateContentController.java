package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateInappropriateContentDto;
import com.mediamicroservice.mediamicroservice.controller.dto.ReportConfirmationDto;
import com.mediamicroservice.mediamicroservice.domain.InappropriateContent;
import com.mediamicroservice.mediamicroservice.service.interfaces.IInappropriateContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inappropriate")
public class InappropriateContentController {

    @Autowired
    private IInappropriateContentService inappropriateContentService;

    @PostMapping
    public InappropriateContent createReport(@RequestBody CreateInappropriateContentDto createInappropriateContentDto) {
        return inappropriateContentService.create(createInappropriateContentDto);
    }

    @GetMapping("/pending")
    public List<CreateInappropriateContentDto> getAllPending(){
        return inappropriateContentService.getAllWithPendingStatus();
    }

    @PostMapping("/confirmation")
    public void confirmation(@RequestBody ReportConfirmationDto reportConfirmationDto){
        inappropriateContentService.reportConfirmation(reportConfirmationDto);
    }
}
