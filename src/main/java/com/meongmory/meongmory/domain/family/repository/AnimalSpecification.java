package com.meongmory.meongmory.domain.family.repository;

import com.meongmory.meongmory.domain.family.entity.Animal;
import com.meongmory.meongmory.global.entity.AnimalType;
import org.springframework.data.jpa.domain.Specification;

public class AnimalSpecification {
    public static Specification<Animal> findSearchword(String searchword){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"%" + searchword + "%");
    }

    public static Specification<Animal> findAnimalType(AnimalType type){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("animalType"), type));
    }
}
