package com.example.familymemberapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity()
@Table(name = "familymember")

public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "familyId")
    private Long familyId;

    @Column(name = "familyName")
    private String familyName;

    @Column(name = "givenName")
    private String givenName;

    @Column(name = "age")
    private int age;

    public FamilyMember( String familyName, String givenName, int age) {

        this.familyName = familyName;
        this.givenName = givenName;
        this.age = age;
    }
}
