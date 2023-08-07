package com.meongmory.meongmory.domain.family.repository;

import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import com.meongmory.meongmory.domain.family.entity.MemberType;
import com.meongmory.meongmory.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
  Optional<FamilyMember> findByFamilyAndUserAndIsEnable(Family family, User user, Boolean isEnable);
  Boolean existsByUserAndTypeAndIsEnable(User user, MemberType type, Boolean isEnable);
}
