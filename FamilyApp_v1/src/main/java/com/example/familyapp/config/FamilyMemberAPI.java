package com.example.familyapp.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FamilyMemberAPI {

    @Value("${family.member.api}")
    private String familyMemberApi;
}
