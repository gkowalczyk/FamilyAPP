package com.example.familyapp.client;

import com.example.familyapp.config.FamilyMemberAPI;
import com.example.familyapp.dto.FamilyDto;
import com.example.familyapp.dto.FamilyMemberDto;
import com.example.familyapp.service.FamilyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static java.util.Optional.ofNullable;

@Component
public class FamilyMemberAPIClient {


    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(FamilyMemberAPIClient.class);


    private final RestTemplate restTemplate;
    private final FamilyMemberAPI familyMemberAPI;
    private final FamilyService familyService;


    public FamilyMemberAPIClient(RestTemplate restTemplate, FamilyMemberAPI familyMemberAPI, FamilyService familyService) {
        this.restTemplate = restTemplate;
        this.familyMemberAPI = familyMemberAPI;
        this.familyService = familyService;
    }

    public List<FamilyMemberDto> responseFamilyMemberDto(Long id) {

        try {
            FamilyMemberDto[] familyMemberResponse = restTemplate
                    .getForObject(familyMemberAPI.getFamilyMemberApi() + id
                            , FamilyMemberDto[].class);

            return ofNullable(familyMemberResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error("!!!!!!!!!!!!!!!!!!" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void sendOfFamilyToMembers(FamilyDto familyDto, Long id) {


        Iterator<FamilyMemberDto> familyMemberDtoIterator = familyDto.getFamilyMemberDtoList().stream()
                .iterator();
        while (familyMemberDtoIterator.hasNext()) {
            FamilyMemberDto familyMemberDto = familyMemberDtoIterator.next();
            for (int n = 0; n <= familyDto.getFamilyMemberDtoList().size(); n++) {
                restTemplate.postForObject(familyMemberAPI.getFamilyMemberApi()
                                + id,
                        familyMemberDto,
                        FamilyMemberDto.class);
                LOGGER.info("Sending.........");
            }
        }
    }
}