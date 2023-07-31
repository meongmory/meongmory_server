package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.global.entity.AnimalType;
import com.meongmory.meongmory.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Animal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;
    private String name;

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @Builder
    public Animal(String name, AnimalType animalType) {
        this.name=name;
        this.animalType = animalType;
    }
}
