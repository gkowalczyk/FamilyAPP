package com.example.familymemberapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Getter
@NoArgsConstructor
public class FamilyMemberDto {

    private Long id;
    private Long familyId;
    private String familyName;
    private String givenName;
    private int age;
}

