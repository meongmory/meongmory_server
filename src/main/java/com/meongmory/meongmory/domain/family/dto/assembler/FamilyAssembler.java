package com.meongmory.meongmory.domain.family.dto.assembler;

import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import com.meongmory.meongmory.domain.family.entity.MemberType;
import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.global.util.Constant;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Component;

import static com.meongmory.meongmory.global.util.Constant.Family.RANDOM_CODE_SIZE;

@Component
public class FamilyAssembler {
    public Family toEntity(String name) {
        return Family.builder()
                .name(name)
                .familyInviteCode(RandomStringUtils.random(RANDOM_CODE_SIZE, true, true))
                .friendInviteCode(RandomStringUtils.random(RANDOM_CODE_SIZE, true, true))
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
