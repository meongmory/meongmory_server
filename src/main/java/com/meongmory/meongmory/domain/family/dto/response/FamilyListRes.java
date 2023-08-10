package com.meongmory.meongmory.domain.family.dto.response;

import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import com.meongmory.meongmory.domain.family.entity.Pet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Schema(description = "가족 구성원 조회 DTO")
public class FamilyListRes {
    @Schema(description = "가족 리스트")
    List<FamilyMemberRes> familyList;
    @Schema(description = "친구 리스트")
    List<FamilyMemberRes> friendList;
    @Schema(description = "반려동물 리스트")
    List<FamilyPetRes> petList;

    public static FamilyListRes toDto(List<FamilyMember> familyList, List<FamilyMember> friendList, List<Pet> petList){
        return FamilyListRes.builder()
                .familyList(familyList.stream().map(FamilyMemberRes::toDto).collect(Collectors.toList()))
                .friendList(friendList.stream().map(FamilyMemberRes::toDto).collect(Collectors.toList()))
                .petList(petList.stream().map(FamilyPetRes::toDto).collect(Collectors.toList()))
                .build();
    }
}
