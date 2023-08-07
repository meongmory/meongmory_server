package com.meongmory.meongmory.domain.family.dto.assembler;

import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import com.meongmory.meongmory.domain.family.entity.MemberType;
import com.meongmory.meongmory.domain.user.entity.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class FamilyAssembler {
    public Family toEntity(String name) {
        return Family.builder()
                .name(name)
                .familyInviteCode(RandomStringUtils.random(7, true, true))
                .friendInviteCode(RandomStringUtils.random(7, true, true))
                .build();
    }

    public FamilyMember toMemberEntity(User user, Family family, MemberType type) {
        return FamilyMember.builder()
                .user(user)
                .family(family)
                .type(type)
                .build();
    }
}
