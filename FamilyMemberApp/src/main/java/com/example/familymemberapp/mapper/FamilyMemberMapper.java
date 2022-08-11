package com.example.familymemberapp.mapper;

import com.example.familymemberapp.domain.FamilyMember;
import com.example.familymemberapp.dto.FamilyMemberDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FamilyMemberMapper {

    public FamilyMember mapToFamilyMember(FamilyMemberDto familyMemberDto) {
        return new FamilyMember(

                familyMemberDto.getFamilyName(),
                familyMemberDto.getGivenName(),
                familyMemberDto.getAge()
        );
    }

    public FamilyMemberDto mapToFamilyMemberDto(FamilyMember familyMember) {
        return new FamilyMemberDto(
                familyMember.getId(),
                familyMember.getFamilyId(),
                familyMember.getFamilyName(),
                familyMember.getGivenName(),
                familyMember.getAge()
        );

    }

    public List<FamilyMemberDto> mapToFamiliesMembersDtoLists(List<FamilyMember> familyMembersList) {
        return familyMembersList.stream()
                .map(this::mapToFamilyMemberDto)
                .collect(Collectors.toList());

    }

    public List<FamilyMember> mapToFamiliesMembersLists(List<FamilyMemberDto> familyMembersListDto) {
        return familyMembersListDto.stream()
                .map(this::mapToFamilyMember)
                .collect(Collectors.toList());
    }
}