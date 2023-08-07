package com.meongmory.meongmory.domain.family.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateFamilyReq {
    @Schema(type = "String", description = "펫 다이어리 이름", example = "댕냥이의 일기장")
    @NotBlank(message = "G0004")
    private String name;
}
