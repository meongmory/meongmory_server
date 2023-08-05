package com.meongmory.meongmory.domain.family.repository;

import com.meongmory.meongmory.domain.family.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
  Optional<Family> findByFamilyIdAndIsEnable(Long familyId, Boolean isEnable);
}
