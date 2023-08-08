package com.meongmory.meongmory.domain.family.service;

import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyReq;
import com.meongmory.meongmory.domain.family.dto.response.AnimalTypeListRes;
import com.meongmory.meongmory.domain.family.dto.response.FamilyInviteCodeRes;
import com.meongmory.meongmory.domain.family.entity.Animal;
import com.meongmory.meongmory.domain.family.entity.Family;
import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import com.meongmory.meongmory.domain.family.entity.MemberType;
import com.meongmory.meongmory.domain.family.repository.AnimalRepository;
import com.meongmory.meongmory.domain.family.repository.AnimalSpecification;
import com.meongmory.meongmory.domain.family.repository.FamilyMemberRepository;
import com.meongmory.meongmory.domain.family.repository.FamilyRepository;
import com.meongmory.meongmory.domain.user.entity.User;
import com.meongmory.meongmory.domain.user.repository.UserRepository;
import com.meongmory.meongmory.global.entity.AnimalType;
import com.meongmory.meongmory.global.exception.BaseException;
import com.meongmory.meongmory.global.exception.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class FamilyService {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final AnimalRepository animalRepository;

    // 가족 생성
    @Transactional
    public void createFamily(CreateFamilyReq createFamilyReq, Long userId) {
        User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));

        // 사용자의 가족 생성은 최대 1번
        if(familyMemberRepository.existsByUserAndTypeAndIsEnable(user, MemberType.OWNER, true)) throw new BaseException(BaseResponseCode.FAMILY_ALREADY_HAD);

        // 가족 생성
        Family family = Family.toEntity(createFamilyReq.getName());
        FamilyMember owner = FamilyMember.toEntity(user, family, MemberType.OWNER);
        familyRepository.save(family);
        familyMemberRepository.save(owner);
    }

    public AnimalTypeListRes getAnimalType(String searchword, String type, Pageable pageable) {
        Specification<Animal> specAnimal = (root, query, criteriaBuilder) -> null;
        // 검색 단어 있음
        if(StringUtils.hasText(searchword)) specAnimal = specAnimal.and(AnimalSpecification.findSearchword(searchword));
        // type 있음
        if(StringUtils.hasText(type)) specAnimal = specAnimal.and(AnimalSpecification.findAnimalType(AnimalType.getAnimalTypeByName(type)));
        specAnimal = specAnimal.and(AnimalSpecification.findAnimalIsEnable(true));
        return AnimalTypeListRes.toDto(animalRepository.findAll(specAnimal, pageable));
    }

    public FamilyInviteCodeRes getFamilyInviteCode(Long familyId, Long userId) {
        User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        Family family = familyRepository.findByFamilyIdAndIsEnable(familyId, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));
        FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(family, user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
        if(!familyMember.getType().equals(MemberType.OWNER)) throw new BaseException(BaseResponseCode.FAMILY_TYPE_ACCESS_DENIED);
        return FamilyInviteCodeRes.toDto(family);
    }
}
