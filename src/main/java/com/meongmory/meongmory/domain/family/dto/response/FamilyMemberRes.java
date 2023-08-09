package com.meongmory.meongmory.domain.family.dto.response;

import com.meongmory.meongmory.domain.family.entity.FamilyMember;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMemberRes {
    @Schema(type = "Long", description = "유저 id", example = "1")
    Long userId;
    @Schema(type = "String", description = "유저 닉네임", example = "루밍")
    String nickname;
    @Schema(type = "String", description = "유저 이미지 키", example = "imgkey.png")
    String imgKey;

    public static FamilyMemberRes toDto(FamilyMember familyMember){
        return FamilyMemberRes.builder()
                .userId(familyMember.getUser().getUserId())
                .nickname(familyMember.getUser().getNickname())
                .imgKey(familyMember.getUser().getImage())
                .build();
    }
}
