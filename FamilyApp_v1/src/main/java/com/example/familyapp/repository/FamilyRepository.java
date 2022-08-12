package com.example.familyapp.repository;

import com.example.familyapp.domain.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends CrudRepository<Family, Long> {
}