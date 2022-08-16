package com.example.familymemberapp.controller;

import com.example.familymemberapp.domain.FamilyMember;
import com.example.familymemberapp.dto.FamilyMemberDto;
import com.example.familymemberapp.mapper.FamilyMemberMapper;
import com.example.familymemberapp.service.FamilyMemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/family")

public class FamilyMemberAppController {

    private final FamilyMemberMapper familyMemberMapper;
    private final FamilyMemberService familyMemberService;

    public FamilyMemberAppController(FamilyMemberMapper familyMemberMapper, FamilyMemberService familyMemberService) {
        this.familyMemberMapper = familyMemberMapper;
        this.familyMemberService = familyMemberService;
    }

    @GetMapping(value = "/{familyListId}")
    public ResponseEntity<List<FamilyMemberDto>> searchFamilyMember(@PathVariable Long familyListId) {
        List<FamilyMember> familyMemberList = familyMemberService.getFamilyMemberById(familyListId);
        return ResponseEntity.ok(familyMemberMapper.mapToFamiliesMembersDtoLists(familyMemberList));
    }

    @PostMapping(value = "/{idMember}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createFamilyMember(@PathVariable Long idMember, @RequestBody FamilyMemberDto familyMemberDto) {
        FamilyMember familyMember = familyMemberMapper.mapToFamilyMember(familyMemberDto);
        familyMember.setFamilyId(idMember);
        familyMemberService.save(familyMember);
        return ResponseEntity.ok().build();
    }
}