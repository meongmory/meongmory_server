package com.meongmory.meongmory.domain.family.entity;

import com.meongmory.meongmory.global.entity.BaseEntity;
import com.meongmory.meongmory.global.entity.Type;
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
    private Type type;

    @Builder
    public Animal(Long animalId, String name, Type type) {
        this.animalId = animalId;
        this.name=name;
        this.type=type;
    }
}
