package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Pet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="animalId")
    private Animal animalId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birth;
    private LocalDate adopt_date;
    private String registration;
    private String imgKey;

    @Builder
    public Pet(Long petId, Animal animalId, String name, Gender gender, LocalDate birth, LocalDate adopt_date, String registration, String imgKey) {
        this.petId = petId;
        this.animalId = animalId;
        this.name=name;
        this.gender=gender;
        this.birth=birth;
        this.adopt_date=adopt_date;
        this.registration=registration;
        this.imgKey = imgKey;
    }
}
