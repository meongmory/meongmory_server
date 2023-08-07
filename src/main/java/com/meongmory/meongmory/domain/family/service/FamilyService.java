package com.meongmory.meongmory.domain.family.service;

import com.meongmory.meongmory.domain.family.dto.assembler.FamilyAssembler;
import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyReq;
import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import com.meongmory.meongmory.domain.family.entity.MemberType;
import com.meongmory.meongmory.domain.family.repository.FamilyMemberRepository;
import com.meongmory.meongmory.domain.family.repository.FamilyRepository;
import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.domain.user.repository.UserRepository;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyService {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyAssembler familyAssembler;
    public void createFamily(CreateFamilyReq createFamilyReq, Long userId) {
        User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));

        // 사용자의 가족 생성은 최대 1번
        if(familyMemberRepository.existsByUserAndTypeAndIsEnable(user, MemberType.OWNER, true)) throw new BaseException(BaseResponseCode.FAMILY_ALREADY_HAD);

        // 가족 생성
        Family family = familyAssembler.toEntity(createFamilyReq.getName());
        FamilyMember owner = familyAssembler.toMemberEntity(user, family, MemberType.OWNER);
        familyRepository.save(family);
        familyMemberRepository.save(owner);
    }
}
