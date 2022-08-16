package com.example.familyapp.controller;

import com.example.familyapp.client.FamilyMemberAPIClient;
import com.example.familyapp.domain.Family;
import com.example.familyapp.dto.FamilyDto;
import com.example.familyapp.exception.FamilyNotFoundException;
import com.example.familyapp.mapper.FamilyMapper;
import com.example.familyapp.service.FamilyService;
import com.example.familyapp.validator.AgeValidatorForFamilyControler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/family")
@RequiredArgsConstructor
public class FamilyController {

    private final AgeValidatorForFamilyControler ageValidatorForFamilyControler;
    private final FamilyService familyService;
    private final FamilyMapper familyMapper;
    private final FamilyMemberAPIClient familyMemberAPIClient;

    @GetMapping(value = "/{familyId}")
    public ResponseEntity<FamilyDto> getFamilyId(@PathVariable Long familyId) throws FamilyNotFoundException {
        FamilyDto familyDto = familyMapper
                .mapToFamilyDto(familyService.getFamilyById(familyId));
        familyDto.setFamilyMemberDtoList(new ArrayList<>(familyMemberAPIClient.responseFamilyMemberDto(familyId)));
        return ResponseEntity.ok(familyDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createFamily(@RequestBody FamilyDto familyDto) throws FamilyNotFoundException {
        Family family = familyMapper.mapToFamily(familyDto);
        family.setId((familyService.getAllFamiliesSizeFromDb() + 1));

        if (!ageValidatorForFamilyControler.ageAdultIsValid(familyDto) || !ageValidatorForFamilyControler.ageInfantsIsValid(familyDto) || !ageValidatorForFamilyControler.ageChildrenIsValid(familyDto)) {
            throw new FamilyNotFoundException("Global http error handler class");
        } else {
            familyService.save(family);
            familyMemberAPIClient.sendOfFamilyToMembers(familyDto, familyService.getAllFamiliesSizeFromDb());
            return ResponseEntity.ok(familyService.getAllFamiliesSizeFromDb());
        }
    }
}

