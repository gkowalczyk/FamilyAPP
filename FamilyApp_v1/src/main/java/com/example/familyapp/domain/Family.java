package com.example.familyapp.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity()
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "familyName")
    private String familyName;

    @Column(name = "nrOfAdults")
    private int nrOfAdults;

    @Column(name = "nrOfChildren")
    private int nrOfChildren;

    @Column(name = "nrOfInfants")
    private int nrOfInfants;


    public Family(Long id, String familyName, int nrOfAdults, int nrOfChildren, int nrOfInfants) {
        this.id = id;
        this.familyName = familyName;
        this.nrOfAdults = nrOfAdults;
        this.nrOfChildren = nrOfChildren;
        this.nrOfInfants = nrOfInfants;
    }
}