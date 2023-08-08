package com.meongmory.meongmory.domain.family.dto.response;

import com.meongmory.meongmory.domain.family.entity.Animal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@Schema(description = "반려동물 품종 목록 조회 DTO")
public class AnimalTypeListRes {

    @Schema(description = "반려동물 품종 목록 리스트")
    private Page<AnimalTypeRes> animalTypeLists;

    public static AnimalTypeListRes toDto(Page<Animal> animals){
        return new AnimalTypeListRes(animals.map(AnimalTypeRes::toDto));
    }
}
