package com.meongmory.meongmory.domain.family.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateFamilyPetReq {
    @Schema(type = "String", description = "반려동물 이름", example = "댕댕이", required = true)
    @NotBlank(message = "G0004")
    private String petName;
    @Schema(type = "String", description = "반려동물 품종 id", example = "1", required = true)
    @NotNull(message = "G0004")
    private Long animalId;
    @Schema(type = "String", description = "반려동물 성별", allowableValues = {"수컷", "암컷", "미상/중성"}, example = "수컷", required = true)
    @NotBlank(message = "G0004")
    private String gender;
    @Schema(type = "Integer", description = "반려동물 나이", example = "10", required = true)
    @NotNull(message = "G0004")
    private Integer birth;
    @Schema(type = "String", description = "반려동물 생년월일(yyyy-MM-dd)", example = "2021-01-20 ", required = true)
    @NotNull(message = "G0004")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate adoptDate;
    @Schema(type = "String", description = "반려동물 등록번호", example = "1010001010")
    private String registration;
    @Schema(type = "String", description = "반려동물 이미지", example = "imgkey.jpg")
    private String imgKey;
}
