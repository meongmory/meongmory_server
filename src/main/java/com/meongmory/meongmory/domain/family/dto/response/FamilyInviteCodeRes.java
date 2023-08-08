package com.meongmory.meongmory.domain.family.dto.response;

import com.meongmory.meongmory.domain.family.entity.Family;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FamilyInviteCodeRes {
    private String familyInviteCode;
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
