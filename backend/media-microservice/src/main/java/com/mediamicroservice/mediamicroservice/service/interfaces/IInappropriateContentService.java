package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateInappropriateContentDto;
import com.mediamicroservice.mediamicroservice.controller.dto.ReportConfirmationDto;
import com.mediamicroservice.mediamicroservice.domain.InappropriateContent;

import java.util.List;

public interface IInappropriateContentService {

    List<InappropriateContent> getAll();

    InappropriateContent create(CreateInappropriateContentDto inappropriateContent);

    List<CreateInappropriateContentDto> getAllWithPendingStatus();

    void reportConfirmation(ReportConfirmationDto reportConfirmationDto);
}
