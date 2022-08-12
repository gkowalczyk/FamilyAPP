package com.example.familymemberapp.service;

import com.example.familymemberapp.domain.FamilyMember;
import com.example.familymemberapp.repository.FamilyMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j

public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;

    public FamilyMemberService(FamilyMemberRepository familyMemberRepository) {
        this.familyMemberRepository = familyMemberRepository;
    }

    public void save(final FamilyMember familyMember) {
        familyMemberRepository.save(familyMember);
    }

    public List<FamilyMember> getAllFamiliesMemberFromDb() {
        Iterable<FamilyMember> familyMembersIterable = familyMemberRepository.findAll();
        List<FamilyMember> familyMembersArrayList = new ArrayList<>();
        familyMembersIterable.forEach(familyMembersArrayList::add);
        return familyMembersArrayList;
    }

    public List<FamilyMember> getFamilyMemberById(final Long familyListId) {

        try {
            log.info("Family ID List has been found from DB");
            return familyMemberRepository.findFamilyMemberByFamilyId(familyListId);
        } catch (NoSuchElementException e) {
            log.info("Empty list");
        }
        return new ArrayList<>();
    }
}
