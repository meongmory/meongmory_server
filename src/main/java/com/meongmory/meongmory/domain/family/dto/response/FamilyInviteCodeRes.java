package com.meongmory.meongmory.domain.family.dto.response;

import com.meongmory.meongmory.domain.family.entity.Family;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "펫 다이어리(가족) 초대 코드 조회 DTO")
public class FamilyInviteCodeRes {
    @Schema(type = "String", description = "가족 초대코드", example = "nl2BpqA")
    private String familyInviteCode;
    @Schema(type = "String", description = "친구 초대코드", example = "nl2BpqA")
    private String friendInviteCode;

    @Builder
    public FamilyInviteCodeRes(String familyInviteCode, String friendInviteCode) {
        this.familyInviteCode = familyInviteCode;
        this.friendInviteCode = friendInviteCode;
    }

    public static FamilyInviteCodeRes toDto(Family family){
        return FamilyInviteCodeRes.builder()
                .familyInviteCode(family.getFamilyInviteCode())
                .friendInviteCode(family.getFriendInviteCode())
                .build();
    }
}
