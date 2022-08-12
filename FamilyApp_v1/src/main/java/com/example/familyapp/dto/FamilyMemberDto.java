package com.example.familyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberDto {

   // private Long id;
    //private Long familyId;
    private String familyName;
    private String givenName;
    private int age;
}
