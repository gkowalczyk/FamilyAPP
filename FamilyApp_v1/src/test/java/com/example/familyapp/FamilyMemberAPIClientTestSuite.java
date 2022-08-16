package com.example.familyapp;

import com.example.familyapp.client.FamilyMemberAPIClient;
import com.example.familyapp.config.FamilyMemberAPI;
import com.example.familyapp.dto.FamilyMemberDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FamilyMemberAPIClientTestSuite {

    @InjectMocks
    private FamilyMemberAPIClient familyMemberAPIClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private FamilyMemberAPI familyMemberAPI;

    @Test
    public void shouldResponseFamilyMemberDto() throws URISyntaxException {

        //Given
        when(familyMemberAPI.getFamilyMemberApi()).thenReturn("http://localhost:8082/family/");
        FamilyMemberDto[] familyMemberDto = new FamilyMemberDto[2];
        familyMemberDto[0] = new FamilyMemberDto("test", "test", 25);
        familyMemberDto[1] = new FamilyMemberDto("test1", "test1", 35);
        URI uri = new URI("http://localhost:8082/family/");
        when(restTemplate.getForObject(uri + "1", FamilyMemberDto[].class)).thenReturn(familyMemberDto);
        //when
        List<FamilyMemberDto> responseFamilyMemberDto = familyMemberAPIClient.responseFamilyMemberDto(1L);
         //then
        assertEquals(2, responseFamilyMemberDto.size());
        assertEquals("test", responseFamilyMemberDto.get(0).getFamilyName());
        assertEquals(35, responseFamilyMemberDto.get(1).getAge());
    }
}
