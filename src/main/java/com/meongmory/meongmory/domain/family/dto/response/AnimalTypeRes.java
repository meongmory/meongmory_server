package com.meongmory.meongmory.domain.family.dto.response;

import com.meongmory.meongmory.domain.family.entity.Animal;
import com.meongmory.meongmory.global.entity.AnimalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AnimalTypeRes {
    @Schema(type = "Long", description = "반려동물 품종 id", example = "1")
    private Long animalId;
    @Schema(type = "String", description = "반려동물 타입", allowableValues = {"강아지", "고양이"}, example = "강아지")
    private String animalType;
    @Schema(type = "String", description = "반려동물 품종 이름", example = "요크셔테리어")
    private String animalName;

    @Builder
    public AnimalTypeRes(Long animalId, String animalType, String animalName) {
        this.animalId = animalId;
        this.animalType = animalType;
        this.animalName = animalName;
    }

    public static AnimalTypeRes toDto(Animal animal){
        return AnimalTypeRes.builder()
                .animalId(animal.getAnimalId())
                .animalType(animal.getAnimalType().getName())
                .animalName(animal.getName())
                .build();
    }
}
