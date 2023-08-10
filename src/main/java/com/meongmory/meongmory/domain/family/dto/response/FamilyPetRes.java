package com.meongmory.meongmory.domain.family.dto.response;

import com.meongmory.meongmory.domain.family.entity.Pet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyPetRes {
    @Schema(type = "Long", description = "반려동물 id", example = "1")
    Long petId;
    @Schema(type = "String", description = "반려동물 이름", example = "댕댕이", required = true)
    String name;
    @Schema(type = "String", description = "반려동물 이미지", example = "imgkey.jpg")
    String imgKey;

    public static FamilyPetRes toDto(Pet pet){
        return FamilyPetRes.builder()
                .petId(pet.getPetId())
                .name(pet.getName())
                .imgKey(pet.getImgKey())
                .build();
    }
}
