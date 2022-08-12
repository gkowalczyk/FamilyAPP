package com.example.familyapp.validator;

import com.example.familyapp.dto.FamilyDto;
import org.springframework.stereotype.Service;

@Service
public class AgeValidatorForFamilyControler {


    public boolean ageAdultIsValid(FamilyDto familyDto) {
        if (familyDto.getNrOfAdults() == familyDto.getFamilyMemberDtoList().stream()
                .map(adult -> adult.getAge())
                .filter(age -> age >= 16)
                .count()) {
            return true;
        }
        return false;
    }

    public boolean ageChildrenIsValid(FamilyDto familyDto) {
        if (familyDto.getNrOfChildren() == familyDto.getFamilyMemberDtoList().stream()
                .map(adult -> adult.getAge())
                .filter(age -> age >= 4 && age < 16)
                .count()) {
            return true;
        }
        return false;
    }

    public boolean ageInfantsIsValid(FamilyDto familyDto) {

        if (familyDto.getNrOfInfants() == familyDto.getFamilyMemberDtoList().stream()
                .map(adult -> adult.getAge())
                .filter(age -> age > 0 && age < 4)
                .count()) {
            return true;
        }
        return false;
    }
}

