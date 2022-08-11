package com.example.familymemberapp.repository;

import com.example.familymemberapp.domain.FamilyMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FamilyMemberRepository extends CrudRepository<FamilyMember, Long> {

    List<FamilyMember> findFamilyMemberByFamilyId(final Long ListFamilyId);
}
