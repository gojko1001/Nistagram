package com.agent.webshop.connection;

import com.agent.webshop.controller.dto.CampaignDto;
import com.agent.webshop.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CampaignApiConnection {
    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8762/campaign-api/web-shop";

    public void createCampaign(CampaignDto campaignDto, String apiKey) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("x-api-key", apiKey);
            restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(campaignDto, headers), ResponseEntity.class);
        } catch (Exception e) {
            throw new BadRequestException("Campaign failed to create");
        }
    }

    public void uploadImages(List<MultipartFile> multipartFiles, String apiKey) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("x-api-key", apiKey);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", multipartFiles);

            HttpEntity<MultiValueMap<String, Object>> requestEntity
                    = new HttpEntity<>(body, headers);

            restTemplate.postForEntity(url + "/upload_img",requestEntity, Object.class);
        } catch (Exception e) {
            throw new BadRequestException("Images failed to upload");
        }
    }
}