package com.meongmory.meongmory.domain.family.service;

import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyPetReq;
import com.meongmory.meongmory.domain.family.dto.request.CreateFamilyReq;
import com.meongmory.meongmory.domain.family.dto.request.InviteFamilyMemberReq;
import com.meongmory.meongmory.domain.family.dto.response.AnimalTypeListRes;
import com.meongmory.meongmory.domain.family.dto.response.FamilyInviteCodeRes;
import com.meongmory.meongmory.domain.family.dto.response.FamilyListRes;
import com.meongmory.meongmory.domain.family.entity.*;
import com.meongmory.meongmory.domain.family.repository.*;
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

import java.time.LocalDate;
import java.util.List;

import static com.meongmory.meongmory.global.exception.BaseResponseCode.FAMILY_OWNER_DELETE_DENIED;

@Service
@RequiredArgsConstructor
public class FamilyService {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final AnimalRepository animalRepository;
    private final PetRepository petRepository;

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

    @Transactional
    public void postFamilyPet(CreateFamilyPetReq createFamilyPetReq, Long familyId, Long userId) {
        User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        Family family = familyRepository.findByFamilyIdAndIsEnable(familyId, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));
        FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(family, user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
        // 친구는 반려동물 생성 불가
        if(familyMember.getType().equals(MemberType.FRIEND)) throw new BaseException(BaseResponseCode.FAMILY_TYPE_ACCESS_DENIED);
        Animal animal = animalRepository.findByAnimalIdAndIsEnable(createFamilyPetReq.getAnimalId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.ANIMAL_NOT_FOUND));

        // todo: pro 버전이 아닌 경우 => 몇 마리의 반려 동물이 가능한지?
        Pet pet = Pet.toEntity(animal, createFamilyPetReq, family, createPetBirthYear(createFamilyPetReq.getBirth()));
        petRepository.save(pet);
    }

    @Transactional
    public void deleteFamilyPet(Long familyId, Long petId, Long userId) {
        User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        Family family = familyRepository.findByFamilyIdAndIsEnable(familyId, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));
        FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(family, user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
        // 친구면, 반려동물 삭제 불가
        if(familyMember.getType().equals(MemberType.FRIEND)) throw new BaseException(BaseResponseCode.FAMILY_TYPE_ACCESS_DENIED);

        Pet pet = this.petRepository.findByPetIdAndIsEnable(petId, true).orElseThrow(() -> new BaseException(BaseResponseCode.PET_NOT_FOUND));
        petRepository.delete(pet);
    }

    public FamilyListRes getFamilyAndPetList(Long familyId, Long userId) {
        User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        Family family = familyRepository.findByFamilyIdAndIsEnable(familyId, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));
        FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(family, user, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));

        List<Pet> familyPetList = petRepository.findAllByFamilyAndIsEnable(family, true);
        List<FamilyMember> familyList = familyMemberRepository.findAllByFamilyAndTypeAndIsEnable(family, MemberType.FAMILY, true);
        List<FamilyMember> friendList = familyMemberRepository.findAllByFamilyAndTypeAndIsEnable(family, MemberType.FRIEND, true);
        familyList.add(familyMember); // owner 저장

        return FamilyListRes.toDto(familyList, friendList, familyPetList);
    }

    public void deleteFamilyMember(Long familyId, Long memberId, Long ownerId) {
        Family family = familyRepository.findByFamilyIdAndIsEnable(familyId, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));

        // owner exception
        User owner = userRepository.findByUserIdAndIsEnable(ownerId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        FamilyMember familyOwner = familyMemberRepository.findByFamilyAndUserAndIsEnable(family, owner, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
        if(!familyOwner.getType().equals(MemberType.OWNER)) throw new BaseException(BaseResponseCode.FAMILY_TYPE_ACCESS_DENIED);

        // member exception
        User member = userRepository.findByUserIdAndIsEnable(memberId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        FamilyMember familyMember = familyMemberRepository.findByFamilyAndUserAndIsEnable(family, member, true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_MEMBER_NOT_FOUND));
        if(familyMember.getType().equals(MemberType.OWNER)) throw new BaseException(FAMILY_OWNER_DELETE_DENIED); // owner 면 삭제 불가능

        // todo: cascade 후 isEnable false test 필요
        familyMemberRepository.delete(familyMember);
    }

    @Transactional
    public void inviteFamilyMember(InviteFamilyMemberReq familyMemberReq, Long userId) {
        User user = userRepository.findByUserIdAndIsEnable(userId, true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        Family family = familyRepository.findByFamilyInviteCodeOrFriendInviteCodeAndIsEnable(familyMemberReq.getFamilyCode(), familyMemberReq.getFamilyCode(), true).orElseThrow(() -> new BaseException(BaseResponseCode.FAMILY_NOT_FOUND));
        if(familyMemberRepository.existsByUserAndFamilyAndIsEnable(user, family, true)) throw new BaseException(BaseResponseCode.FAMILY_EXIST);
        if (family.getFamilyInviteCode().equals(familyMemberReq.getFamilyCode())) familyMemberRepository.save(FamilyMember.toEntity(user, family, MemberType.FAMILY));
        else familyMemberRepository.save(FamilyMember.toEntity(user, family, MemberType.OWNER));
    }

    // abstract method
    private LocalDate createPetBirthYear(Integer birth){
        return LocalDate.now().minusYears(birth-1L);
    }

    private Integer createPetBirthAge(LocalDate localDate){return LocalDate.now().getYear()-localDate.getYear() - 1;}
}
