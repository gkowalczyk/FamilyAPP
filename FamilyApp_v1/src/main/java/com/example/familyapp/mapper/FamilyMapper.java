package com.example.familyapp.mapper;

import com.example.familyapp.domain.Family;
import com.example.familyapp.dto.FamilyDto;
import org.springframework.stereotype.Component;


@Component
public class FamilyMapper {

    public Family mapToFamily(FamilyDto familyDto) {
        return new Family(
                familyDto.getId(),
                familyDto.getFamilyName(),
                familyDto.getNrOfAdults(),
                familyDto.getNrOfChildren(),
                familyDto.getNrOfInfants()

        );
    }

    public FamilyDto mapToFamilyDto(Family family) {
        return new FamilyDto(
                family.getId(),
                family.getFamilyName(),
                family.getNrOfAdults(),
                family.getNrOfChildren(),
                family.getNrOfInfants()
        );
    }
}
