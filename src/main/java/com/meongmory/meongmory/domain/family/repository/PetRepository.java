package com.meongmory.meongmory.domain.family.repository;

import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
  Optional<Pet> findByPetIdAndIsEnable(Long petId, Boolean isEnable);
  List<Pet> findAllByFamilyAndIsEnable(Family family, Boolean isEnable);
}
