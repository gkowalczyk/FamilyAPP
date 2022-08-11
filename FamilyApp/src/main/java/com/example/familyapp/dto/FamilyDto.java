package com.example.familyapp.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FamilyDto {

    private Long id;

    private String familyName;

    private int nrOfAdults;

    private int nrOfChildren;

    private int nrOfInfants;

    private List<FamilyMemberDto> familyMemberDtoList = new ArrayList<>();

    public FamilyDto(Long id, String familyName, int nrOfAdults, int nrOfChildren, int nrOfInfants) {
        this.id = id;
        this.familyName = familyName;
        this.nrOfAdults = nrOfAdults;
        this.nrOfChildren = nrOfChildren;
        this.nrOfInfants = nrOfInfants;
    }
}


