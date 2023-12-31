package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyPetReq;
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
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="familyId")
    private Family family;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birth;
    private LocalDate adoptDate;
    private String registration;
    private String imgKey;

    @Builder
    public Pet(Animal animal, String name, Gender gender, LocalDate birth, LocalDate adoptDate, String registration, String imgKey, Family family) {
        this.animal = animal;
        this.name=name;
        this.gender=gender;
        this.birth=birth;
        this.adoptDate=adoptDate;
        this.registration=registration;
        this.imgKey = imgKey;
        this.family = family;
    }

    public static Pet toEntity(Animal animal, CreateFamilyPetReq petInfo, Family family, LocalDate birth){
        return Pet.builder()
                .animal(animal)
                .family(family)
                .gender(Gender.getGenderByName(petInfo.getGender()))
                .name(petInfo.getPetName())
                .birth(birth)
                .adoptDate(petInfo.getAdoptDate())
                .imgKey(petInfo.getImgKey())
                .registration(petInfo.getRegistration())
                .build();
    }
}
