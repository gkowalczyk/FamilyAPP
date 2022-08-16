package com.example.familyapp;

import com.example.familyapp.client.FamilyMemberAPIClient;
import com.example.familyapp.controller.FamilyController;
import com.example.familyapp.domain.Family;
import com.example.familyapp.dto.FamilyDto;
import com.example.familyapp.dto.FamilyMemberDto;
import com.example.familyapp.mapper.FamilyMapper;
import com.example.familyapp.service.FamilyService;
import com.example.familyapp.validator.AgeValidatorForFamilyControler;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(FamilyController.class)
public class FamilyControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FamilyMemberAPIClient familyMemberAPIClient;

    @MockBean
    private FamilyMapper familyMapper;

    @MockBean
    private FamilyService familyService;
    @MockBean
    private AgeValidatorForFamilyControler ageValidatorForFamilyControler;

    @Test
    void shouldFetchFamilyBoards() throws Exception {

        //given

        Family family = new Family(1L, "familyname1", 2, 0, 1);
        FamilyDto familyDto = new FamilyDto(1L, "familynamedto1", 2, 0, 1);
        List<FamilyMemberDto> familyMemberDtoList = List.of(
                new FamilyMemberDto("Nowak", "Anna", 25),
                new FamilyMemberDto("Nowak", "Piotr", 25),
                new FamilyMemberDto("Nowak", "Kasia", 5)
        );

        when(familyMapper.mapToFamilyDto(family)).thenReturn(familyDto);
        when(familyService.getFamilyById(1L)).thenReturn(family);
        when(familyMemberAPIClient.responseFamilyMemberDto(family.getId())).thenReturn(familyMemberDtoList);

        //when and then
        mockMvc
                .perform(get("/family/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.familyName", Matchers.is("familynamedto1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.familyMemberDtoList[0].givenName", Matchers.is("Anna")));
    }
}