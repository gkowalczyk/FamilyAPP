package com.example.familyapp.service;

import com.example.familyapp.domain.Family;
import com.example.familyapp.exception.FamilyNotFoundException;
import com.example.familyapp.repository.FamilyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public Family save(final Family family) {
        log.info("Adding family to DB");
        return familyRepository.save(family);
    }

    public Long getAllFamiliesSizeFromDb() {

        Iterable<Family> familyIterable = familyRepository.findAll();
        List<Family> familyArrayList = new ArrayList<>();
        familyIterable.forEach(familyArrayList::add);
        Long max = familyArrayList.stream()
                .mapToLong(o -> o.getId())
                .max()
                .orElse(0);
        return max;
    }

    public Family getFamilyById(final Long id) throws FamilyNotFoundException {
        return familyRepository.findById(id).orElseThrow(() -> new FamilyNotFoundException
                ("Family not found for id: " + id));
    }
}


