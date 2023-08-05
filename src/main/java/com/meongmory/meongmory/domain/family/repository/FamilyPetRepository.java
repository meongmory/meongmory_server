package com.meongmory.meongmory.domain.family.repository;

import com.meongmory.meongmory.domain.family.entity.FamilyPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyPetRepository extends JpaRepository<FamilyPet, Long> {
}
